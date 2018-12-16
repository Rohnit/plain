
@file:JvmName("DesignerNewsV1SourceRemover")

package io.plainapp.core.data.prefs

import android.content.SharedPreferences
import androidx.core.content.edit

/**
 * When DesignerNews updated from v1 to v2 of their API, they removed the recent data source. This
 * file checks/removes the data source key from [SharedPreferences].
 */

private const val SOURCE_DESIGNER_NEWS_RECENT = "SOURCE_DESIGNER_NEWS_RECENT"

/**
 * Checks if [key] is SOURCE_DESIGNER_NEWS_RECENT data source and if so, removes it from [prefs].
 * @return `true` if [key] is SOURCE_DESIGNER_NEWS_RECENT data source & was removed,
 * otherwise `false`.
 */
fun checkAndRemoveDesignerNewsRecentSource(key: String, prefs: SharedPreferences): Boolean {
    var removed = false
    if (key == SOURCE_DESIGNER_NEWS_RECENT) {
        prefs.edit { remove(key) }
        removed = true
    }
    return removed
}
