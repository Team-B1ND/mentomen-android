package kr.hs.dgsw.mentomenv2.feature.search

import androidx.lifecycle.MutableLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kr.hs.dgsw.mentomenv2.base.BaseViewModel
import kr.hs.dgsw.mentomenv2.domain.model.Post
import kr.hs.dgsw.mentomenv2.domain.usecase.post.GetAllPostUseCase
import kr.hs.dgsw.mentomenv2.domain.util.Utils
import kr.hs.dgsw.mentomenv2.state.PostState
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val getAllPostUseCase: GetAllPostUseCase
) : BaseViewModel() {
    val keyWord = MutableLiveData<String>("")
    val isLoading = MutableLiveData<Boolean>(false)
    private val _errorFlow = MutableSharedFlow<String?>()
    val errorFlow = _errorFlow.asSharedFlow()
    val postState = MutableStateFlow<PostState>(PostState())

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
}
