package io.plainapp.core.designernews.data.login

import io.plainapp.core.designernews.data.login.model.LoggedInUser
import io.plainapp.core.data.Result

class LoginRepository(
        private val localDataSource: LoginLocalDataSource,
        private val remoteDataSource: LoginRemoteDataSource
) {

    // local cache of the user object, so we don't retrieve it from the local storage every time
    // we need it
    var user: LoggedInUser? = null
        private set

    val isLoggedIn: Boolean
        get() = user != null

    init {
        user = localDataSource.user
    }

    fun logout() {
        user = null

        localDataSource.logout()
        remoteDataSource.logout()
    }

    suspend fun login(username: String, password: String): Result<LoggedInUser> {
        val result = remoteDataSource.login(username, password)

        if (result is Result.Success) {
            setLoggedInUser(result.data)
        }
        return result
    }

    private fun setLoggedInUser(loggedInUser: LoggedInUser) {
        user = loggedInUser
        localDataSource.user = user
    }

    companion object {
        @Volatile
        private var INSTANCE: LoginRepository? = null

        fun getInstance(
                localDataSource: LoginLocalDataSource,
                remoteDataSource: LoginRemoteDataSource
        ): LoginRepository {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: LoginRepository(
                        localDataSource,
                        remoteDataSource
                ).also { INSTANCE = it }
            }
        }
    }
}