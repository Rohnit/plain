
@file:JvmName("DribbbleV1SourceRemover")

package io.plainapp.core.data.prefs

import android.content.SharedPreferences
import androidx.core.content.edit

/**
 * When Dribbble updated from v1 to v2 of their API, they removed a number of data sources. This
 * file checks/removes data source keys from [SharedPreferences] referring to any of the removed
 * API sources.
 */

private const val V1_SOURCE_KEY_PREFIX = "SOURCE_DRIBBBLE_"

/**
 * Checks if [key] is a Dribbble v1 API data source and if so, removes it from [prefs].
 * @return `true` if [key] is a Dribbble v1 data source & was removed, otherwise `false`.
 */
fun checkAndRemove(key: String, prefs: SharedPreferences): Boolean {
    var removed = false
    if (isDribbbleV1Source(key)) {
        prefs.edit { remove(key) }
        removed = true
    }
    return removed
}

private fun isDribbbleV1Source(key: String) = key.startsWith(V1_SOURCE_KEY_PREFIX)
