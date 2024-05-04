package com.b1nd.mentomen.feature.home

import com.b1nd.mentomen.base.BaseViewModel
import com.b1nd.mentomen.domain.model.NoticeStatus
import com.b1nd.mentomen.domain.usecase.notice.GetNoticeStatusUseCase
import com.b1nd.mentomen.domain.usecase.post.GetAllPostUseCase
import com.b1nd.mentomen.domain.usecase.post.GetPostsByTagUseCase
import com.b1nd.mentomen.state.PostState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class HomeViewModel
    @Inject
    constructor(
        private val getAllPostUseCase: GetAllPostUseCase,
        private val getPostsByTagUseCase: GetPostsByTagUseCase,
        private val getNoticeStatusUseCase: GetNoticeStatusUseCase,
    ) : BaseViewModel() {
        val postState = MutableStateFlow<PostState>(PostState())
        private val _isLoading = MutableStateFlow<Boolean>(false)
        val isLoading = _isLoading.asStateFlow()
        val notificationStatus = MutableStateFlow<NoticeStatus>(NoticeStatus.NONE)

        fun getAllPost() {
            getAllPostUseCase().safeApiCall(
                _isLoading,
                successAction = {
                    postState.value =
                        PostState(
                            postList = it!!,
                            tag = "ALL",
                        )
                },
            )
        }

        fun onClickDesignBtn() {
            if (postState.value.tag == "DESIGN") {
                getAllPost()
            } else {
                getPostsByTagUseCase("DESIGN").safeApiCall(
                    _isLoading,
                    successAction = {
                        postState.value =
                            PostState(
                                postList = it,
                                tag = "DESIGN",
                            )
                    },
                )
            }
        }

        fun onClickWebBtn() {
            if (postState.value.tag == "WEB") {
                getAllPost()
            } else {
                getPostsByTagUseCase("WEB").safeApiCall(
                    _isLoading,
                    successAction = {
                        postState.value =
                            PostState(
                                postList = it,
                                tag = "WEB",
                            )
                    },
                )
            }
        }

        fun onClickAndroidBtn() {
            if (postState.value.tag == "ANDROID") {
                getAllPost()
            } else {
                getPostsByTagUseCase("ANDROID").safeApiCall(
                    _isLoading,
                    successAction = {
                        postState.value =
                            PostState(
                                postList = it,
                                tag = "ANDROID",
                            )
                    },
                )
            }
        }

        fun onClickServerBtn() {
            if (postState.value.tag == "SERVER") {
                getAllPost()
            } else {
                getPostsByTagUseCase("SERVER").safeApiCall(
                    _isLoading,
                    successAction = {
                        postState.value =
                            PostState(
                                postList = it,
                                tag = "SERVER",
                            )
                    },
                )
            }
        }

        fun onClickIOSBtn() {
            if (postState.value.tag == "IOS") {
                getAllPost()
            } else {
                getPostsByTagUseCase("IOS").safeApiCall(
                    _isLoading,
                    successAction = {
                        postState.value =
                            PostState(
                                postList = it,
                                tag = "IOS",
                            )
                    },
                )
            }
        }

        fun getNoticeStatus() {
            getNoticeStatusUseCase().safeApiCall(
                _isLoading,
                {
                    notificationStatus.value = it!!
                },
                isEmitError = false,
            )
        }
    }
