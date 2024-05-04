package com.b1nd.mentomen.data.service

import com.b1nd.mentomen.data.response.base.BaseResponse
import com.b1nd.mentomen.domain.model.Post
import com.b1nd.mentomen.domain.model.User
import retrofit2.Call
import retrofit2.http.GET

interface MyService {
    @GET("user/my")
    fun getUserInfo(): Call<BaseResponse<User>>

    @GET("user/post")
    fun getMyPost(): Call<BaseResponse<List<Post>>>
}
