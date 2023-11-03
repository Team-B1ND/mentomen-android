package kr.hs.dgsw.mentomenv2.feature.main

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import kr.hs.dgsw.mentomenv2.base.BaseViewModel
import kr.hs.dgsw.mentomenv2.domain.model.Post
import kr.hs.dgsw.mentomenv2.domain.model.StdInfo
import kr.hs.dgsw.mentomenv2.domain.repository.PostRepository
import kr.hs.dgsw.mentomenv2.domain.usecase.post.GetAllPostUseCase
import kr.hs.dgsw.mentomenv2.domain.usecase.post.GetPostsByTagUseCase
import kr.hs.dgsw.mentomenv2.domain.usecase.post.PostUseCases
import kr.hs.dgsw.mentomenv2.domain.util.NetworkResult
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

    fun getAllPost() {F
        postRepository.getAllPost().onEach {
            postState.value = PostState(
                postList = it
            )
        }.launchIn(viewModelScope)
    }

    fun onClickDesignBtn() {


//            : () -> Flow<NetworkResult<List<Post>>> = {
//        postRepository.getPostByTag("DESIGN").safeApiCall(
//            successAction = {
//                postState.value = PostState(
//                    postList = it,
//                    tag = "DESIGN"
//                )
//            },
//            errorAction = {
//                _errorFlow.tryEmit(it)
//            },
//            isLoading = isLoading
//        )
    }

    fun onClickWebBtn() {}
//    : () -> Flow<NetworkResult<List<Post>>> = {
//        getPostsByTagUseCase("WEB").safeApiCall(
//            successAction = {
//                postState.value = PostState(
//                    postList = it,
//                    tag = "WEB"
//                )
//            },
//            errorAction = {
//                _errorFlow.tryEmit(it)
//            },
//            isLoading = isLoading
//        )
//    }

    fun onClickAndroidBtn() {}
//    : () -> Flow<NetworkResult<List<Post>>> = {
//        getPostsByTagUseCase("ANDROID").safeApiCall(
//            successAction = {
//                postState.value = PostState(
//                    postList = it,
//                    tag = "ANDROID"
//                )
//            },
//            errorAction = {
//                _errorFlow.tryEmit(it)
//            },
//            isLoading = isLoading
//        )
//    }

    fun onClickServerBtn() {}
//    : () -> Flow<NetworkResult<List<Post>>> = {
//        getPostsByTagUseCase("SERVER").safeApiCall(
//            successAction = {
//                postState.value = PostState(
//                    postList = it,
//                    tag = "SERVER"
//                )
//            },
//            errorAction = {
//                _errorFlow.tryEmit(it)
//            },
//            isLoading = isLoading
//        )
//    }

    fun onClickIOSBtn() {}
//    : () -> Flow<NetworkResult<List<Post>>> = {
//        getPostsByTagUseCase("IOS").safeApiCall(
//            successAction = {
//                postState.value = PostState(
//                    postList = it,
//                    tag = "IOS"
//                )
//            },
//            errorAction = {
//                _errorFlow.tryEmit(it)
//            },
//            isLoading = isLoading
//        )
//    }
}
