
package io.plainapp.core.designernews.data.login.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "logged_in_user")
data class LoggedInUser(
    @PrimaryKey
    @ColumnInfo(name = "id")
    @SerializedName("id")
    val id: Long,

    @ColumnInfo(name = "first_name")
    @SerializedName("first_name")
    val firstName: String,

    @ColumnInfo(name = "last_name")
    @SerializedName("last_name")
    val lastName: String,

    @ColumnInfo(name = "display_name")
    @SerializedName("display_name")
    val displayName: String,

    @ColumnInfo(name = "potrait_url")
    @SerializedName("portrait_url")
    val portraitUrl: String? = null,

    @ColumnInfo(name = "upvotes")
    @SerializedName("upvotes")
    val upvotes: List<Long>
)
