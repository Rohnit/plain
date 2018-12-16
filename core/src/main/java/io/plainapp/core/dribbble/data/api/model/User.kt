
package io.plainapp.core.dribbble.data.api.model

import com.google.gson.annotations.SerializedName

/**
 * Models a dribbble user
 */
data class User(
    @SerializedName("id") val id: Long,
    @SerializedName("name") val name: String,
    @SerializedName("username") val username: String,
    @SerializedName("avatar_url") val avatarUrl: String
) {

    val highQualityAvatarUrl: String by lazy {
        avatarUrl.replace("/normal/", "/original/")
    }
}
