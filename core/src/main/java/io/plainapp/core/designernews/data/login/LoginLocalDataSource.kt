package io.plainapp.core.designernews.data.login

import android.content.SharedPreferences

class LoginLocalDataSource(private val prefs: SharedPreferences) {


    companion object {
        const val DESIGNER_NEWS_PREF = "DESIGNER_NEWS_PREF"
        private const val KEY_USER_ID = "KEY_USER_ID"
        private const val KEY_USER_NAME = "KEY_USER_NAME"
        private const val KEY_USER_AVATAR = "KEY_USER_AVATAR"
    }

}