package kr.hs.dgsw.mentomenv2.data.service

import kr.hs.dgsw.mentomenv2.data.response.base.BaseResponse
import kr.hs.dgsw.mentomenv2.domain.model.Post
import kr.hs.dgsw.mentomenv2.domain.params.PostSubmitParam
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface PostService {
    @GET("post/read-all")
    suspend fun getAllPost(): BaseResponse<List<Post>>

    @GET("post/read-all/{tag}")
    suspend fun getPostByTag(
        @Path("tag") tag: String,
    ): BaseResponse<List<Post>>

    @POST("post/submit")
    suspend fun submitPost(
        @Body postSubmitParam: PostSubmitParam,
    ): BaseResponse<Unit>

    @GET("post/read-one/{id}")
    suspend fun getPostById(
        @Path("id") id: Int,
    ): BaseResponse<Post>
}
