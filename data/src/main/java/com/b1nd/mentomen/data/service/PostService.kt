package com.b1nd.mentomen.data.service

import com.b1nd.mentomen.data.response.base.BaseResponse
import com.b1nd.mentomen.domain.model.Post
import com.b1nd.mentomen.domain.params.PostEditParam
import com.b1nd.mentomen.domain.params.PostSubmitParam
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.PATCH
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

    @DELETE("post/delete/{postId}")
    suspend fun deletePostById(
        @Path("postId") postId: Int,
    ): BaseResponse<Unit>

    @PATCH("post/update")
    suspend fun editPost(
        @Body postUpdateDto: PostEditParam,
    ): BaseResponse<Unit>
}
