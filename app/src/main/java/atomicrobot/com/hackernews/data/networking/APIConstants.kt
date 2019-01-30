package atomicrobot.com.hackernews.data.networking

import atomicrobot.com.hackernews.data.model.Post
import atomicrobot.com.hackernews.data.model.Comments
import atomicrobot.com.hackernews.data.model.User
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Created by trong on 11/11/18.
 *
 * Class Description: Basic interface class that contains the API Constant
 * In this case a simple Get call
 */

interface APIConstants {
    @GET("topstories.json")
    fun getTopStories(): Call<List<Long>>

    @GET("item/{itemId}.json")
    fun getStoryItem(@Path("itemId") itemId: Long): Call<Post>

    @GET("item/{itemId}.json")
    fun getCommentItem(@Path("itemId") itemId: Int): Call<Comments>

    @GET("user/{user}.json")
    fun getUser(@Path("user") user: String): Call<User>
}