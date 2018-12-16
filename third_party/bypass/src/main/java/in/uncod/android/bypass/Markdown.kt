
package `in`.uncod.android.bypass

import android.content.res.ColorStateList
import androidx.annotation.ColorInt

/**
 *  Interface for Markdown capabilities.
 */
interface Markdown {
    /**
     * Create a spannable [CharSequence] from a text containing markdown data.
     */
    fun markdownToSpannable(
        content: String,
        linksColor: ColorStateList,
        @ColorInt highlightColor: Int,
        callback: LoadImageCallback?
    ): CharSequence
}
