
package io.plainapp.core.dribbble.data.api.model

import io.plainapp.core.dribbble.data.api.model.Images.ImageSize.NORMAL_IMAGE_SIZE
import io.plainapp.core.dribbble.data.api.model.Images.ImageSize.TWO_X_IMAGE_SIZE

/**
 * Models links to the various quality of images of a shot. One of [hidpi] or [normal] must be
 * non-null.
 */
data class Images(
    val hidpi: String? = null,
    val normal: String? = null,
    val teaser: String? = null
) {

    fun best(): String {
        return hidpi ?: normal!!
    }

    fun bestSize(): ImageSize {
        return if (hidpi != null) TWO_X_IMAGE_SIZE else NORMAL_IMAGE_SIZE
    }

    enum class ImageSize(val width: Int, val height: Int) {
        NORMAL_IMAGE_SIZE(400, 300),
        TWO_X_IMAGE_SIZE(800, 600);

        operator fun component1() = width
        operator fun component2() = height
    }
}
