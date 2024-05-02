package kr.hs.dgsw.mentomenv2.feature.detail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kr.hs.dgsw.mentomenv2.base.BaseViewModel
import kr.hs.dgsw.mentomenv2.domain.model.StdInfo
import kr.hs.dgsw.mentomenv2.domain.usecase.my.GetMyInfoUseCase
import kr.hs.dgsw.mentomenv2.domain.usecase.post.DeletePostByIdUseCase
import kr.hs.dgsw.mentomenv2.domain.usecase.post.GetPostByIdUseCase
import kr.hs.dgsw.mentomenv2.domain.util.Log
import javax.inject.Inject

@HiltViewModel
class DetailViewModel
    @Inject
    constructor(
        private val getMyInfoUseCase: GetMyInfoUseCase,
        private val getPostByIdUseCase: GetPostByIdUseCase,
        private val deletePostByIdUseCase: DeletePostByIdUseCase,
    ) : BaseViewModel() {
        val myUserId = MutableLiveData<Int>()
        val myProfileImg = MutableLiveData<String?>()

        val author = MutableLiveData<Int>()
        val tag = MutableLiveData<String>()
        val content = MutableLiveData<String>()
        val imgUrls = MutableLiveData<List<String>>()
        val createDateTime = MutableLiveData<String>("2023-11-06T14:28:51.528245")
        val stdInfo = MutableLiveData<StdInfo>(StdInfo(3, 4, 6))
        val profileImg = MutableLiveData<String?>()
        val userName = MutableLiveData<String>()
        val postId = MutableLiveData<Int>()
        val isLogin = MutableStateFlow<Boolean>(false)

        private val _isLoading = MutableStateFlow<Boolean>(false)
        val isLoading = _isLoading.asStateFlow()

        init {
            getMyInfoUseCase().safeApiCall(
                isLoading = _isLoading,
                {
                    isLogin.value = true
                    myUserId.value = it?.userId
                    myProfileImg.value = it?.profileImage ?: ""
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
                    Log.d("observegetUserInfo: ", it?.userId.toString())
                    myUserId.value = it?.userId
                    myProfileImg.value = it?.profileImage ?: ""
                },
            )
        }

        fun getPostInfo() {
            getPostByIdUseCase(id = postId.value ?: 0).safeApiCall(
                isLoading = _isLoading,
                { post ->
                    isLogin.value = true
                    author.value = post?.author
                    tag.value = post?.tag
                    content.value = post?.content
                    imgUrls.value = post?.imgUrls ?: emptyList()
                    createDateTime.value = post?.createDateTime
                    stdInfo.value = post?.stdInfo
                    profileImg.value = post?.profileUrl
                    userName.value = post?.userName
                },
            )
        }

        fun deletePost() {
            Log.d("DetailViewModel", "deletePostCall")
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
