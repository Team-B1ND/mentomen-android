package kr.hs.dgsw.mentomenv2.data.service

import kr.hs.dgsw.mentomenv2.data.request.CommentSubmitRequest
import kr.hs.dgsw.mentomenv2.data.request.CommentUpdateRequest
import kr.hs.dgsw.mentomenv2.data.response.base.BaseResponse
import kr.hs.dgsw.mentomenv2.domain.model.Comment
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Path

interface CommentService {
    @GET("comment/read/{postId}")
    fun getCommentList(
        @Path("postId") postId: Int
    ): BaseResponse<List<Comment>>

    @POST("comment/submit")
    fun submitComment(
        @Body commentSubmitDto: CommentSubmitRequest
    ): BaseResponse<Unit>

    @DELETE("comment/delete/{id}")
    fun deleteComment(
        @Path("id") id: Int
    ): BaseResponse<Unit>

    @PATCH("comment/update")
    fun updateComment(
        @Body commentUpdateDto: CommentUpdateRequest
    ): BaseResponse<Unit>
}