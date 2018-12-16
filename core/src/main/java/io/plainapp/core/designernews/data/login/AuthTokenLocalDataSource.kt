
package io.plainapp.core.designernews.data.login

import android.content.SharedPreferences
import androidx.core.content.edit

/**
 * Local storage for auth token.
 */
class AuthTokenLocalDataSource(private val prefs: SharedPreferences) {

    private var _authToken: String? = prefs.getString(KEY_ACCESS_TOKEN, null)

    /**
     * Auth token used for requests that require authentication
     */
    var authToken: String? = _authToken
        set(value) {
            prefs.edit { putString(KEY_ACCESS_TOKEN, value) }
            field = value
        }

    fun clearData() {
        prefs.edit { KEY_ACCESS_TOKEN to null }
        authToken = null
    }

    companion object {
        const val DESIGNER_NEWS_AUTH_PREF = "DESIGNER_NEWS_AUTH_PREF"
        private const val KEY_ACCESS_TOKEN = "KEY_ACCESS_TOKEN"

        @Volatile
        private var INSTANCE: AuthTokenLocalDataSource? = null

        fun getInstance(
            sharedPreferences: SharedPreferences
        ): AuthTokenLocalDataSource {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: AuthTokenLocalDataSource(sharedPreferences).also {
                    INSTANCE = it
                }
            }
        }
    }
}
