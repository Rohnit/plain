
package io.plainapp.core.designernews.data.stories.model

import com.google.gson.annotations.SerializedName
import java.util.Date

fun getDefaultStoryUrl(id: Long) = "https://www.designernews.co/click/stories/$id"

fun StoryResponse.toStory() = Story(
    id = id,
    title = title,
    url = url,
    comment = comment,
    commentHtml = comment_html,
    commentCount = comment_count,
    voteCount = vote_count,
    userId = links.user,
    createdAt = created_at,
    links = links
)

/**
 * Models a Designer News story response.
 */
data class StoryResponse(
    @SerializedName("id") val id: Long,
    @SerializedName("title") val title: String,
    @SerializedName("url") var url: String? = getDefaultStoryUrl(id),
    @SerializedName("comment") val comment: String? = null,
    @SerializedName("comment_html") val comment_html: String? = null,
    @SerializedName("comment_count") val comment_count: Int = 0,
    @SerializedName("vote_count") val vote_count: Int = 0,
    @SerializedName("created_at") val created_at: Date,
    @SerializedName("links") val links: StoryLinks
)
