package kr.hs.dgsw.mentomenv2.feature.main

import androidx.lifecycle.MutableLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kr.hs.dgsw.mentomenv2.base.BaseViewModel
import kr.hs.dgsw.mentomenv2.domain.model.Post
import kr.hs.dgsw.mentomenv2.domain.model.StdInfo
import kr.hs.dgsw.mentomenv2.domain.usecase.post.PostUseCases
import kr.hs.dgsw.mentomenv2.domain.util.NetworkResult
import kr.hs.dgsw.mentomenv2.state.PostState
import javax.inject.Inject

@HiltViewModel
class HomeFragmentViewModel @Inject constructor(
    private val postUseCases: PostUseCases,
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
        postUseCases.getAllPostUseCase().safeApiCall(
            successAction = {
                postState.value = PostState(
                    postList = it,
                    tag = "ALL"
                )
            },
            errorAction = {
                postState.value = PostState(
                    error = it ?: "게시글을 불러오는데 실패했습니다."
                )
            },
            isLoading = isLoading
        )
    }

    fun onClickDesignBtn(): () -> Flow<NetworkResult<List<Post>>> = {
        postUseCases.getPostsByTagUseCases("DESIGN").safeApiCall(
            successAction = {
                postState.value = PostState(
                    postList = it,
                    tag = "DESIGN"
                )
            },
            errorAction = {
                _errorFlow.tryEmit(it)
            },
            isLoading = isLoading
        )
    }

    fun onClickWebBtn(): () -> Flow<NetworkResult<List<Post>>> = {
        postUseCases.getPostsByTagUseCases("WEB").safeApiCall(
            successAction = {
                postState.value = PostState(
                    postList = it,
                    tag = "WEB"
                )
            },
            errorAction = {
                _errorFlow.tryEmit(it)
            },
            isLoading = isLoading
        )
    }

    fun onClickAndroidBtn(): () -> Flow<NetworkResult<List<Post>>> = {
        postUseCases.getPostsByTagUseCases("ANDROID").safeApiCall(
            successAction = {
                postState.value = PostState(
                    postList = it,
                    tag = "ANDROID"
                )
            },
            errorAction = {
                _errorFlow.tryEmit(it)
            },
            isLoading = isLoading
        )
    }

    fun onClickServerBtn(): () -> Flow<NetworkResult<List<Post>>> = {
        postUseCases.getPostsByTagUseCases("SERVER").safeApiCall(
            successAction = {
                postState.value = PostState(
                    postList = it,
                    tag = "SERVER"
                )
            },
            errorAction = {
                _errorFlow.tryEmit(it)
            },
            isLoading = isLoading
        )
    }

    fun onClickIOSBtn(): () -> Flow<NetworkResult<List<Post>>> = {
        postUseCases.getPostsByTagUseCases("IOS").safeApiCall(
            successAction = {
                postState.value = PostState(
                    postList = it,
                    tag = "IOS"
                )
            },
            errorAction = {
                _errorFlow.tryEmit(it)
            },
            isLoading = isLoading
        )
    }
}
