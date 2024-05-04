package com.b1nd.mentomen.data.service

import com.b1nd.mentomen.domain.model.Comment
import com.b1nd.mentomen.data.request.CommentSubmitRequest
import com.b1nd.mentomen.data.request.CommentUpdateRequest
import com.b1nd.mentomen.data.response.base.BaseResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Path

interface CommentService {
    @GET("comment/read/{postId}")
    fun getCommentList(
        @Path("postId") postId: Int,
    ): Call<BaseResponse<List<Comment>>>

    @POST("comment/submit")
    fun submitComment(
        @Body commentSubmitDto: CommentSubmitRequest,
    ): Call<BaseResponse<Unit>>

    @DELETE("comment/delete/{id}")
    fun deleteComment(
        @Path("id") id: Int,
    ): Call<BaseResponse<Unit>>

    @PATCH("comment/update")
    fun updateComment(
        @Body commentUpdateDto: CommentUpdateRequest,
    ): Call<BaseResponse<Unit>>
}
