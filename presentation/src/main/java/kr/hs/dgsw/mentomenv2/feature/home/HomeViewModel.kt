package kr.hs.dgsw.mentomenv2.feature.home

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kr.hs.dgsw.mentomenv2.base.BaseViewModel
import kr.hs.dgsw.mentomenv2.domain.model.NoticeStatus
import kr.hs.dgsw.mentomenv2.domain.usecase.notice.GetNoticeStatusUseCase
import kr.hs.dgsw.mentomenv2.domain.usecase.post.GetAllPostUseCase
import kr.hs.dgsw.mentomenv2.domain.usecase.post.GetPostsByTagUseCase
import kr.hs.dgsw.mentomenv2.state.PostState
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
