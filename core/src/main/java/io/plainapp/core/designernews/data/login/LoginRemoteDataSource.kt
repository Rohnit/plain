package io.plainapp.core.designernews.data.login

import io.plainapp.core.BuildConfig
import io.plainapp.core.designernews.data.api.DesignerNewsService
import io.plainapp.core.designernews.data.login.model.LoggedInUser
import io.plainapp.core.designernews.data.login.model.toLoggedInUser
import io.plainapp.core.data.Result
import io.plainapp.core.util.safeApiCall
import java.io.IOException

class LoginRemoteDataSource(
        private val tokenLocalDataSource: AuthTokenLocalDataSource,
        val service: DesignerNewsService
) {

    /**
     * Log out by cleaning up the auth token
     */
    fun logout() {
        tokenLocalDataSource.authToken = null
    }

    suspend fun login(username: String, password: String) = safeApiCall(
            call = { requestLogin(username, password) },
            errorMessage = "Error logging in"
    )

    private suspend fun requestLogin(username: String, password: String): Result<LoggedInUser> {
        val response = service.login(buildLoginParams(username, password)).await()
        if (response.isSuccessful) {
            val body = response.body()
            if (body != null) {
                val token = body.accessToken
                tokenLocalDataSource.authToken = token
                return requestUser()
            }
        }
        return Result.Error(IOException("Access token retrieval failed ${response.code()} ${response.message()}"))
    }

    private suspend fun requestUser(): Result<LoggedInUser> {
        val response = service.getAuthedUser().await()
        if (response.isSuccessful) {
            val users = response.body()
            if (users != null && users.isNotEmpty()) {
                return Result.Success(users[0].toLoggedInUser())
            }
        }
        return Result.Error(IOException("Failed to get authed user ${response.code()} ${response.message()}"))
    }

    private fun buildLoginParams(username: String, password: String): Map<String, String> {
        return mapOf(
                "client_id" to BuildConfig.DESIGNER_NEWS_CLIENT_ID,
                "client_secret" to BuildConfig.DESIGNER_NEWS_CLIENT_SECRET,
                "grant_type" to "password",
                "username" to username,
                "password" to password
        )
    }
}