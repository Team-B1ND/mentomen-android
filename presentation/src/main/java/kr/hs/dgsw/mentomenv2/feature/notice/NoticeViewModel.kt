package kr.hs.dgsw.mentomenv2.feature.notice

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kr.hs.dgsw.mentomenv2.base.BaseViewModel
import kr.hs.dgsw.mentomenv2.domain.model.Notice
import kr.hs.dgsw.mentomenv2.domain.usecase.notice.GetNoticesUseCase
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
