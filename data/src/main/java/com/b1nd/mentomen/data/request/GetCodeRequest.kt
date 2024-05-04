package com.b1nd.mentomen.data.request

data class GetCodeRequest(
    val id: String,
    val pw: String,
    val clientId: String,
    val redirectUrl: String,
    val state: String? = null,
)
