
package io.plainapp.core.designernews.data.stories.model

import com.google.gson.annotations.SerializedName
import io.plainapp.core.data.PlaidItem
import java.util.Date

fun getDefaultUrl(id: Long) = "https://www.designernews.co/click/stories/$id"

/**
 * Models a Designer News story.
 * TODO split this into StoryRequest and Story, so we can keep the object immutable.
 */
data class Story(
    @SerializedName("id") override val id: Long,
    @SerializedName("title") override val title: String,
    @SerializedName("url")
    override var url: String? = getDefaultUrl(id),
    @SerializedName("comment") val comment: String? = null,
    @SerializedName("comment_html") val commentHtml: String? = null,
    @SerializedName("comment_count") val commentCount: Int = 0,
    @SerializedName("vote_count") val voteCount: Int = 0,
    @SerializedName("user_id") val userId: Long,
    @SerializedName("created_at") val createdAt: Date,
    @SerializedName("links") val links: StoryLinks,
    @Deprecated("Removed in DN API V2")
    @SerializedName("user_display_name") val userDisplayName: String? = null,
    @Deprecated("Removed in DN API V2")
    @SerializedName("user_portrait_url") val userPortraitUrl: String? = null,
    @SerializedName("user_job") val userJob: String? = null
) : PlaidItem(id, title, url)
