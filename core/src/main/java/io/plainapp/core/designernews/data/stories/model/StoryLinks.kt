
package io.plainapp.core.designernews.data.stories.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

/**
 * Models story links received from DesignerNews v2 API
 */
@Parcelize
data class StoryLinks(
    @SerializedName("user") val user: Long,
    @SerializedName("comments") val comments: List<Long>,
    @SerializedName("upvotes") val upvotes: List<Long>,
    @SerializedName("downvotes") val downvotes: List<Long>
) : Parcelable
