package io.plainapp.core.designernews.data.login

import android.content.SharedPreferences
import androidx.core.content.edit
import io.plainapp.core.designernews.data.login.model.LoggedInUser

class LoginLocalDataSource(private val prefs: SharedPreferences) {

    /**
     * Instance of the logged in user. If missing, null is returned
     */
    var user: LoggedInUser?
        get() {
            val userId = prefs.getLong(KEY_USER_ID, 0L)
            val username = prefs.getString(KEY_USER_NAME, null)
            val userAvatar = prefs.getString(KEY_USER_AVATAR, null)
            if (userId == 0L || username == null) {
                return null
            }
            // TODO save the entire user
            return LoggedInUser(
                    id = userId,
                    firstName = "",
                    lastName = "",
                    displayName = username,
                    portraitUrl = userAvatar,
                    upvotes = emptyList()
            )
        }
        set(value) {
            if (value != null) {
                prefs.edit {
                    putLong(KEY_USER_ID, value.id)
                    putString(KEY_USER_NAME, value.displayName)
                    putString(KEY_USER_AVATAR, value.portraitUrl)
                }
            }
        }

    /**
     * Clear all data related to this Designer News instance: user data and access token
     */
    fun logout() {
        prefs.edit {
            putLong(KEY_USER_ID, 0L)
            putString(KEY_USER_NAME, null)
            putString(KEY_USER_AVATAR, null)
        }
    }

    companion object {
        const val DESIGNER_NEWS_PREF = "DESIGNER_NEWS_PREF"
        private const val KEY_USER_ID = "KEY_USER_ID"
        private const val KEY_USER_NAME = "KEY_USER_NAME"
        private const val KEY_USER_AVATAR = "KEY_USER_AVATAR"
    }

}