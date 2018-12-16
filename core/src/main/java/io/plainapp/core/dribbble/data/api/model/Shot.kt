
package io.plainapp.core.dribbble.data.api.model

import com.google.gson.annotations.SerializedName
import io.plainapp.core.data.PlaidItem
import java.util.Date

/**
 * Models a dibbble shot
 */
data class Shot(
    @SerializedName("id") override val id: Long,
    @SerializedName("title") override val title: String,
    @SerializedName("description") val description: String,
    @SerializedName("images") val images: Images,
    @SerializedName("views_count") val viewsCount: Int = 0,
    @SerializedName("likes_count") val likesCount: Int = 0,
    @SerializedName("created_at") val createdAt: Date? = null,
    @SerializedName("html_url") val htmlUrl: String = "https://dribbble.com/shots/$id",
    @SerializedName("animated") val animated: Boolean = false,
    @SerializedName("user") val user: User
) : PlaidItem(id, title, htmlUrl) {

    // todo move this into a decorator
    var hasFadedIn = false
}
