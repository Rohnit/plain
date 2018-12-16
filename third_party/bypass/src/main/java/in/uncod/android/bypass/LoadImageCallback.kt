
package `in`.uncod.android.bypass

import `in`.uncod.android.bypass.style.ImageLoadingSpan

/**
 * Called when an image url was found in parsed markdown.
 * This image has to be loaded in order to display.
 */
interface LoadImageCallback {
    /**
     * Callback called when an image found in a markdown document should be loaded.
     * @param src The source (url) of the image.
     * @param loadingSpan A placeholder span making where the image should be inserted.
     */
    fun loadImage(src: String, loadingSpan: ImageLoadingSpan)
}
