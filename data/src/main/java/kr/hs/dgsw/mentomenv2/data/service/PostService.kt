package kr.hs.dgsw.mentomenv2.data.service

import kotlinx.coroutines.flow.Flow
import kr.hs.dgsw.mentomenv2.data.response.base.BaseResponse
import kr.hs.dgsw.mentomenv2.domain.model.Post
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface PostService {
    @GET("post/read-all")
    suspend fun getAllPost(): BaseResponse<List<Post>>

    @GET("post/read-all/{tag}")
    suspend fun getPostByTag(
        @Path("tag") tag: String
    ): BaseResponse<List<Post>>
}
