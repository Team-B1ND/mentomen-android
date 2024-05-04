package com.b1nd.mentomen.feature.search

import androidx.lifecycle.MutableLiveData
import com.b1nd.mentomen.base.BaseViewModel
import com.b1nd.mentomen.domain.usecase.post.GetAllPostUseCase
import com.b1nd.mentomen.state.PostState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
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
