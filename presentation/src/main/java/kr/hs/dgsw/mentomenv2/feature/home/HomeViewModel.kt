package kr.hs.dgsw.mentomenv2.feature.home

import androidx.lifecycle.MutableLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kr.hs.dgsw.mentomenv2.base.BaseViewModel
import kr.hs.dgsw.mentomenv2.domain.model.Post
import kr.hs.dgsw.mentomenv2.domain.usecase.post.GetAllPostUseCase
import kr.hs.dgsw.mentomenv2.domain.usecase.post.GetPostsByTagUseCase
import kr.hs.dgsw.mentomenv2.domain.util.Utils
import kr.hs.dgsw.mentomenv2.state.PostState
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getAllPostUseCase: GetAllPostUseCase,
    private val getPostsByTagUseCase: GetPostsByTagUseCase
) : BaseViewModel() {
    val postState = MutableStateFlow<PostState>(PostState())
    private val _errorFlow = MutableSharedFlow<String?>()
    val errorFlow = _errorFlow.asSharedFlow()
    private val isLoading: MutableLiveData<Boolean> = MutableLiveData(false)
    var allPosts: List<Post>? = null

    init {
        getAllPost()
    }

    fun getAllPost() {
        if(postState.value.tag != "ALL") {
            getAllPostUseCase.invoke().safeApiCall(
                isLoading,
                successAction = {
                    allPosts = it
                    postState.value = PostState(
                        postList = it,
                        tag = "ALL"
                    )
                },
                errorAction = {
                    _errorFlow.tryEmit(Utils.NETWORK_ERROR_MESSAGE)
                }
            )
        }
    }

    fun onClickDesignBtn() {
        getPostsByTagUseCase("DESIGN").safeApiCall(
            isLoading,
            successAction = {
                if (postState.value.tag != "DESIGN") {
                    postState.value = PostState(
                        postList = it,
                        tag = "DESIGN"
                    )
                } else {
                    postState.value = PostState(
                        postList = allPosts,
                        tag = "ALL"
                    )
                }
            },
            errorAction = {
                _errorFlow.tryEmit(Utils.NETWORK_ERROR_MESSAGE)
            }
        )
    }

    fun onClickWebBtn() {
        getPostsByTagUseCase("WEB").safeApiCall(
            isLoading,
            successAction = {
                if (postState.value.tag != "WEB") {
                    postState.value = PostState(
                        postList = it,
                        tag = "WEB"
                    )
                } else {
                    postState.value = PostState(
                        postList = allPosts,
                        tag = "ALL"
                    )
                }
            },
            errorAction = {
                _errorFlow.tryEmit(Utils.NETWORK_ERROR_MESSAGE)
            }
        )
    }

    fun onClickAndroidBtn() {
        getPostsByTagUseCase("ANDROID").safeApiCall(
            isLoading,
            successAction = {
                if (postState.value.tag != "ANDROID") {
                    postState.value = PostState(
                        postList = it,
                        tag = "ANDROID"
                    )
                } else {
                    postState.value = PostState(
                        postList = allPosts,
                        tag = "ALL"
                    )
                }
            },
            errorAction = {
                _errorFlow.tryEmit(Utils.NETWORK_ERROR_MESSAGE)
            }
        )
    }

    fun onClickServerBtn() {
        getPostsByTagUseCase("SERVER").safeApiCall(
            isLoading,
            successAction = {
                if (postState.value.tag != "SERVER") {
                    postState.value = PostState(
                        postList = it,
                        tag = "SERVER"
                    )
                } else {
                    postState.value = PostState(
                        postList = allPosts,
                        tag = "ALL"
                    )
                }
            },
            errorAction = {
                _errorFlow.tryEmit(Utils.NETWORK_ERROR_MESSAGE)
            }
        )
    }

    fun onClickIOSBtn() {
        getPostsByTagUseCase("IOS").safeApiCall(
            isLoading,
            successAction = {
                if (postState.value.tag != "IOS") {
                    postState.value = PostState(
                        postList = it,
                        tag = "IOS"
                    )
                } else {
                    postState.value = PostState(
                        postList = allPosts,
                        tag = "ALL"
                    )
                }
            },
            errorAction = {
                _errorFlow.tryEmit(Utils.NETWORK_ERROR_MESSAGE)
            }
        )
    }
}
