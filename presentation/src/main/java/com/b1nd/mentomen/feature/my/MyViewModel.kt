package com.b1nd.mentomen.feature.my

import androidx.lifecycle.MutableLiveData
import com.b1nd.mentomen.R
import com.b1nd.mentomen.base.BaseViewModel
import com.b1nd.mentomen.domain.model.NoticeStatus
import com.b1nd.mentomen.domain.model.Post
import com.b1nd.mentomen.domain.model.StdInfo
import com.b1nd.mentomen.domain.repository.DataStoreRepository
import com.b1nd.mentomen.domain.repository.MyRepository
import com.b1nd.mentomen.domain.usecase.notice.GetNoticeStatusUseCase
import com.b1nd.mentomen.domain.util.Log
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class MyViewModel
    @Inject
    constructor(
        private val dataStoreRepository: DataStoreRepository,
        private val myRepository: MyRepository,
        private val getNoticeStatusUseCase: GetNoticeStatusUseCase,
    ) : BaseViewModel() {
        val stdInfo: MutableLiveData<StdInfo> = MutableLiveData(StdInfo(0, 0, 0))
        val userName: MutableLiveData<String> = MutableLiveData("")
        val userProfileUrl: MutableLiveData<String> = MutableLiveData("")
        val post = MutableLiveData<List<Post>>()
        private val _isLoading = MutableStateFlow(false)
        val isLoading = _isLoading.asStateFlow()
        val notificationState = MutableStateFlow(NoticeStatus.NONE)

        fun getMyInfo() {
            myRepository.getMyInfo().safeApiCall(
                _isLoading,
                {
                    userName.value = it?.name ?: ""
                    stdInfo.value = it?.stdInfo ?: StdInfo(0, 0, 0)
                    userProfileUrl.value = it?.profileImage ?: R.drawable.ic_default_user.toString()
                },
            )
        }

        fun getMyPost() {
            myRepository.getMyPost().safeApiCall(
                _isLoading,
                {
                    post.value = it
                },
                isEmitError = false,
            )
        }

        fun getNotificationStatus() {
            getNoticeStatusUseCase().safeApiCall(
                _isLoading,
                {
                    notificationState.value = it!!
                },
                isEmitError = false,
            )
        }

        fun clearDataStore() {
            dataStoreRepository.clearData().safeApiCall(
                _isLoading,
                {
                    Log.d("logout: ", "dataStore 비우기 성공")
                },
            )
        }
    }
