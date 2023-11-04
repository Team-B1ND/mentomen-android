package kr.hs.dgsw.mentomenv2.feature.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.launchIn
import kr.hs.dgsw.mentomenv2.base.BaseViewModel
import kr.hs.dgsw.mentomenv2.domain.model.Post
import kr.hs.dgsw.mentomenv2.domain.model.StdInfo
import kr.hs.dgsw.mentomenv2.domain.repository.PostRepository
import kr.hs.dgsw.mentomenv2.domain.util.Utils
import kr.hs.dgsw.mentomenv2.state.PostState
import javax.inject.Inject

@HiltViewModel
class HomeFragmentViewModel @Inject constructor(
    private val postRepository: PostRepository,
) : BaseViewModel() {
    val postState = MutableStateFlow<PostState>(
        PostState(
            postList = listOf(
                Post(
                    1, "123123123",
                    listOf("https://flexible.img.hani.co.kr/flexible/normal/970/601/imgdb/original/2023/1102/20231102503524.jpg"),
                    "123",
                    123,
                    "https://flexible.img.hani.co.kr/flexible/normal/970/601/imgdb/original/2023/1102/20231102503524.jpg",
                    StdInfo(1, 1, 1),
                    "ANDROID",
                    "123",
                    "123",
                    "123"
                )
            )
        )
    )
    private val _errorFlow = MutableSharedFlow<String?>()
    val errorFlow = _errorFlow.asSharedFlow()

    private val isLoading: MutableLiveData<Boolean> = MutableLiveData(false)

    init {
        getAllPost()
    }

    fun getAllPost() {
        postRepository.getAllPost().safeApiCall(
            isLoading,
            successAction = {
                postState.value = PostState(
                    postList = it,
                    tag = "ALL"
                )
            },
            errorAction = {
                _errorFlow.tryEmit(Utils.NETWORK_ERROR_MESSAGE)
            }
        )
            .launchIn(viewModelScope)
    }

    fun onClickDesignBtn() {
        postRepository.getPostByTag("DESIGN").safeApiCall(
            isLoading,
            successAction = {
                postState.value = PostState(
                    postList = it,
                    tag = "DESIGN"
                )
            },
            errorAction = {
                _errorFlow.tryEmit(Utils.NETWORK_ERROR_MESSAGE)
            }
        )
            .launchIn(viewModelScope)
    }

    fun onClickWebBtn() {
        postRepository.getPostByTag("WEB").safeApiCall(
            isLoading,
            successAction = {
                postState.value = PostState(
                    postList = it,
                    tag = "WEB"
                )
            },
            errorAction = {
                _errorFlow.tryEmit(Utils.NETWORK_ERROR_MESSAGE)
            }
        )
            .launchIn(viewModelScope)
    }

    fun onClickAndroidBtn() {
        postRepository.getPostByTag("ANDROID").safeApiCall(
            isLoading,
            successAction = {
                postState.value = PostState(
                    postList = it,
                    tag = "ANDROID"
                )
            },
            errorAction = {
                _errorFlow.tryEmit(Utils.NETWORK_ERROR_MESSAGE)
            }
        )
            .launchIn(viewModelScope)
    }

    fun onClickServerBtn() {
        postRepository.getPostByTag("SERVER").safeApiCall(
            isLoading,
            successAction = {
                postState.value = PostState(
                    postList = it,
                    tag = "SERVER"
                )
            },
            errorAction = {
                _errorFlow.tryEmit(Utils.NETWORK_ERROR_MESSAGE)
            }
        )
            .launchIn(viewModelScope)
    }

    fun onClickIOSBtn() {
        postRepository.getPostByTag("IOS").safeApiCall(
            isLoading,
            successAction = {
                postState.value = PostState(
                    postList = it,
                    tag = "IOS"
                )
            },
            errorAction = {
                _errorFlow.tryEmit(Utils.NETWORK_ERROR_MESSAGE)
            }
        )
            .launchIn(viewModelScope)
    }
}
