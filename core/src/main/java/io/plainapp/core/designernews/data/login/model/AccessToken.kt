
package io.plainapp.core.designernews.data.login.model

import com.google.gson.annotations.SerializedName

/**
 * Models a Designer News API access token
 */
data class AccessToken(@SerializedName("access_token") val accessToken: String)
