package com.b1nd.mentomen.data.response.base

data class BaseResponse<T>(
    val status: Int,
    val message: String,
    val data: T,
)
