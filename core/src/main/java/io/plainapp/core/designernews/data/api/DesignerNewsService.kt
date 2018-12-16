package io.plainapp.core.designernews.data.api

import io.plainapp.core.data.api.EnvelopePayload
import io.plainapp.core.designernews.data.login.model.AccessToken
import io.plainapp.core.designernews.data.login.model.LoggedInUserResponse
import io.plainapp.core.designernews.data.stories.model.StoryResponse
import io.plainapp.core.dribbble.data.api.model.User
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.*

interface DesignerNewsService {

    @EnvelopePayload("stories")
    @GET("api/v2/stories")
    fun getStories(@Query("page") page: Int?): Deferred<Response<List<StoryResponse>>>

    @EnvelopePayload("stories")
    @GET("api/v1/stories/search")
    fun search(
            @Query("query") query: String,
            @Query("page") page: Int?
    ): Deferred<Response<List<StoryResponse>>>

    @EnvelopePayload("users")
    @GET("api/v2/users/{ids}")
    fun getUsers(@Path("ids") userids: String): Deferred<Response<List<User>>>

    @EnvelopePayload("users")
    @GET("api/v2/me")
    fun getAuthedUser(): Deferred<Response<List<LoggedInUserResponse>>>

    @FormUrlEncoded
    @POST("oauth/token")
    fun login(@FieldMap loginParams: Map<String, String>): Deferred<Response<AccessToken>>

    /*@EnvelopePayload("story")
    @POST("api/v2/stories/{id}/upvote")
    fun upvoteStory(@Path("id") storyId: Long): Call<Story>*/

    /*@Headers("Content-Type: application/vnd.api+json")
    @POST("api/v2/upvotes")
    fun upvoteStoryV2(@Body request: UpvoteStoryRequest): Deferred<Response<Unit>>*/

    /*@EnvelopePayload("stories")
    @Headers("Content-Type: application/vnd.api+json")
    @POST("api/v2/stories")
    fun postStory(@Body story: NewStoryRequest): Call<List<Story>>*/

    /*@EnvelopePayload("comments")
    @GET("api/v2/comments/{ids}")
    fun getComments(@Path("ids") commentIds: String): Deferred<Response<List<CommentResponse>>>*/

    /*@Headers("Content-Type: application/vnd.api+json")
    @POST("api/v2/comments")
    fun comment(@Body comment: NewCommentRequest): Deferred<Response<PostCommentResponse>>*/

    /*@FormUrlEncoded
    @POST("api/v1/comments/{id}/reply")
    fun replyToComment(
            @Path("id") commentId: Long,
            @Field("comment[body]") comment: String
    ): Call<Comment>*/

    /*@Headers("Content-Type: application/vnd.api+json")
    @POST("api/v2/comment_upvotes")
    fun upvoteComment(@Body request: UpvoteCommentRequest): Deferred<Response<Unit>>*/

    companion object {
        const val ENDPOINT = "https://www.designernews.co/"
    }

}