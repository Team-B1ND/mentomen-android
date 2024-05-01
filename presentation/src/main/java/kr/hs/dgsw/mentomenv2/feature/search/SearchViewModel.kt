package kr.hs.dgsw.mentomenv2.feature.search

import androidx.lifecycle.MutableLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kr.hs.dgsw.mentomenv2.base.BaseViewModel
import kr.hs.dgsw.mentomenv2.domain.usecase.post.GetAllPostUseCase
import kr.hs.dgsw.mentomenv2.state.PostState
import javax.inject.Inject

@HiltViewModel
class SearchViewModel
    @Inject
    constructor(
        private val getAllPostUseCase: GetAllPostUseCase,
    ) : BaseViewModel() {
        val keyWord = MutableLiveData<String>("")
        private val _isLoading = MutableStateFlow<Boolean>(false)
        val isLoading = _isLoading.asStateFlow()
        val postState = MutableStateFlow<PostState>(PostState())

        fun getAllPost() {
            getAllPostUseCase().safeApiCall(
                _isLoading,
                successAction = {
                    postState.value =
                        PostState(
                            postList = it,
                            tag = "ALL",
                        )
                },
            )
        }
    }
