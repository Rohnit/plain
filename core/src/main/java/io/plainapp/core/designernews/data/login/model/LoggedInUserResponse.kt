
package io.plainapp.core.designernews.data.login.model

import com.google.gson.annotations.SerializedName

fun LoggedInUserResponse.toLoggedInUser(): LoggedInUser {
    return LoggedInUser(
        id = id,
        firstName = first_name,
        lastName = last_name,
        displayName = display_name,
        portraitUrl = portrait_url,
        upvotes = userLinks.upvotes
    )
}

/**
 * Models a Designer News logged in user response
 */
class LoggedInUserResponse(
    @SerializedName("id") val id: Long,
    @SerializedName("first_name") val first_name: String,
    @SerializedName("last_name") val last_name: String,
    @SerializedName("display_name") val display_name: String,
    @SerializedName("portrait_url") val portrait_url: String? = null,
    @SerializedName("links") val userLinks: UserLinks
)

class UserLinks(@SerializedName("comment_upvotes") val upvotes: List<Long>)
