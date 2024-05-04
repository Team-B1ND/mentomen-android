package com.b1nd.mentomen.feature.notice

import com.b1nd.mentomen.base.BaseViewModel
import com.b1nd.mentomen.domain.model.Notice
import com.b1nd.mentomen.domain.usecase.notice.GetNoticesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class NoticeViewModel
    @Inject
    constructor(
        private val getNoticesUseCase: GetNoticesUseCase,
    ) : BaseViewModel() {
        private val _isLoading = MutableStateFlow<Boolean>(false)
        val isLoading = _isLoading.asStateFlow()

        val noticeState = MutableStateFlow<List<Notice>>(emptyList())

        fun getNotices() {
            getNoticesUseCase().safeApiCall(
                _isLoading,
                { notices ->
                    noticeState.update {
                        notices ?: emptyList()
                    }
                },
            )
        }
    }
