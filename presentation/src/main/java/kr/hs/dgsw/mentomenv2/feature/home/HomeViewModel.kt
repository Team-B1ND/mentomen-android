package kr.hs.dgsw.mentomenv2.feature.home

import androidx.lifecycle.MutableLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kr.hs.dgsw.mentomenv2.base.BaseViewModel
import kr.hs.dgsw.mentomenv2.domain.model.Post
import kr.hs.dgsw.mentomenv2.domain.usecase.my.GetMyInfoUseCase
import kr.hs.dgsw.mentomenv2.domain.usecase.post.GetAllPostUseCase
import kr.hs.dgsw.mentomenv2.domain.usecase.post.GetPostsByTagUseCase
import kr.hs.dgsw.mentomenv2.domain.util.Utils
import kr.hs.dgsw.mentomenv2.state.PostState
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getAllPostUseCase: GetAllPostUseCase,
    private val getPostsByTagUseCase: GetPostsByTagUseCase,
    private val getMyInfoUseCase: GetMyInfoUseCase,
) : BaseViewModel() {
    val postState = MutableStateFlow<PostState>(PostState())
    private val _errorFlow = MutableSharedFlow<String?>()
    val errorFlow = _errorFlow.asSharedFlow()
    private val isLoading: MutableLiveData<Boolean> = MutableLiveData(false)

    init {
//        getMyInfo()
        getAllPost()
    }

    fun getMyInfo() {
        getMyInfoUseCase.invoke().safeApiCall(
            isLoading,
            successAction = {
                getAllPost()
            },
            errorAction = {
                _errorFlow.tryEmit(Utils.NETWORK_ERROR_MESSAGE)
            },
        )
    }

    fun getAllPost() {
        getAllPostUseCase.invoke().safeApiCall(
            isLoading,
            successAction = {
                postState.value =
                    PostState(
                        postList = it,
                        tag = "ALL",
                    )
            },
            errorAction = {
                _errorFlow.tryEmit(Utils.NETWORK_ERROR_MESSAGE)
            },
        )
    }

    fun onClickDesignBtn() {
        if (postState.value.tag == "DESIGN") {
            getAllPost()
        } else {
            getPostsByTagUseCase("DESIGN").safeApiCall(
                isLoading,
                successAction = {
                    postState.value =
                        PostState(
                            postList = it,
                            tag = "DESIGN",
                        )
                },
                errorAction = {
                    _errorFlow.tryEmit(Utils.NETWORK_ERROR_MESSAGE)
                },
            )
        }
    }

    fun onClickWebBtn() {
        if (postState.value.tag == "WEB") {
            getAllPost()
        } else {
            getPostsByTagUseCase("WEB").safeApiCall(
                isLoading,
                successAction = {
                    postState.value =
                        PostState(
                            postList = it,
                            tag = "WEB",
                        )
                },
                errorAction = {
                    _errorFlow.tryEmit(Utils.NETWORK_ERROR_MESSAGE)
                },
            )
        }
    }

    fun onClickAndroidBtn() {
        if (postState.value.tag == "ANDROID") {
            getAllPost()
        } else {
            getPostsByTagUseCase("ANDROID").safeApiCall(
                isLoading,
                successAction = {
                    postState.value =
                        PostState(
                            postList = it,
                            tag = "ANDROID",
                        )
                },
                errorAction = {
                    _errorFlow.tryEmit(Utils.NETWORK_ERROR_MESSAGE)
                },
            )
        }
    }

    fun onClickServerBtn() {
        if (postState.value.tag == "SERVER") {
            getAllPost()
        } else {
            getPostsByTagUseCase("SERVER").safeApiCall(
                isLoading,
                successAction = {
                    postState.value =
                        PostState(
                            postList = it,
                            tag = "SERVER",
                        )
                },
                errorAction = {
                    _errorFlow.tryEmit(Utils.NETWORK_ERROR_MESSAGE)
                },
            )
        }
    }

    fun onClickIOSBtn() {
        if (postState.value.tag == "IOS") {
            getAllPost()
        } else {
            getPostsByTagUseCase("IOS").safeApiCall(
                isLoading,
                successAction = {
                    postState.value =
                        PostState(
                            postList = it,
                            tag = "IOS",
                        )
                },
                errorAction = {
                    _errorFlow.tryEmit(Utils.NETWORK_ERROR_MESSAGE)
                },
            )
        }
    }
}
