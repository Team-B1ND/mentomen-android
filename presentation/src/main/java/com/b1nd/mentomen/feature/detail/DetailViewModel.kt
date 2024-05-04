package com.b1nd.mentomen.feature.detail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.b1nd.mentomen.base.BaseViewModel
import com.b1nd.mentomen.domain.model.StdInfo
import com.b1nd.mentomen.domain.usecase.my.GetMyInfoUseCase
import com.b1nd.mentomen.domain.usecase.post.DeletePostByIdUseCase
import com.b1nd.mentomen.domain.usecase.post.GetPostByIdUseCase
import com.b1nd.mentomen.domain.util.Log
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel
    @Inject
    constructor(
        private val getMyInfoUseCase: GetMyInfoUseCase,
        private val getPostByIdUseCase: GetPostByIdUseCase,
        private val deletePostByIdUseCase: DeletePostByIdUseCase,
    ) : BaseViewModel() {
        val myUserId = MutableStateFlow<Int>(0)
        val myProfileImg = MutableLiveData<String?>()

        val author = MutableStateFlow<Int>(0)
        val tag = MutableLiveData<String>()
        val content = MutableStateFlow<String>("")
        val imgUrls = MutableLiveData<List<String>>()
        val createDateTime = MutableStateFlow<String>("2023-11-06T14:28:51.528245")
        val stdInfo = MutableStateFlow<StdInfo>(StdInfo(3, 4, 6))
        val profileImg = MutableLiveData<String?>()
        val userName = MutableStateFlow<String>("도현욱")
        val postId = MutableLiveData<Int>()
        val isLogin = MutableStateFlow<Boolean>(false)

        private val _isLoading = MutableStateFlow<Boolean>(false)
        val isLoading = _isLoading.asStateFlow()

        init {
            getMyInfoUseCase().safeApiCall(
                isLoading = _isLoading,
                {
                    isLogin.value = true
                    myUserId.value = it!!.userId
                    myProfileImg.value = it.profileImage ?: ""
                },
                {
                    Log.e("DetailViewModel", "getMyInfoFail")
                    isLogin.value = false
                },
                isEmitError = false,
            )
        }

        fun getUserInfo() {
            getMyInfoUseCase().safeApiCall(
                isLoading = _isLoading,
                {
                    isLogin.value = true
                    myUserId.value = it!!.userId
                    myProfileImg.value = it.profileImage ?: ""
                },
            )
        }

        fun getPostInfo() {
            getPostByIdUseCase(id = postId.value ?: 0).safeApiCall(
                isLoading = _isLoading,
                { post ->
                    isLogin.value = true
                    author.value = post!!.author
                    tag.value = post.tag
                    content.value = post.content
                    imgUrls.value = post.imgUrls ?: emptyList()
                    createDateTime.value = post.createDateTime
                    stdInfo.value = post.stdInfo
                    profileImg.value = post.profileUrl
                    userName.value = post.userName
                },
            )
        }

        fun deletePost() {
            deletePostByIdUseCase(id = postId.value ?: 0).safeApiCall(
                isLoading = _isLoading,
                {
                    viewModelScope.launch {
                        viewEvent(DELETE_POST)
                    }
                },
            )
        }

        companion object Event {
            const val DELETE_POST = 1
        }
    }
