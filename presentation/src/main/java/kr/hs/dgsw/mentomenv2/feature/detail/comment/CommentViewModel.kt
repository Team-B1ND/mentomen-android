package kr.hs.dgsw.mentomenv2.feature.detail.comment

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kr.hs.dgsw.mentomenv2.base.BaseViewModel
import kr.hs.dgsw.mentomenv2.domain.params.CommentSubmitParam
import kr.hs.dgsw.mentomenv2.domain.params.CommentUpdateParam
import kr.hs.dgsw.mentomenv2.domain.repository.CommentRepository
import kr.hs.dgsw.mentomenv2.domain.util.Log
import kr.hs.dgsw.mentomenv2.state.CommentState
import javax.inject.Inject

@HiltViewModel
class CommentViewModel
    @Inject
    constructor(
        private val commentRepository: CommentRepository,
    ) : BaseViewModel() {
        val commentState = MutableStateFlow<CommentState>(CommentState())
        val postId = MutableLiveData<Int>()
        private val _isLoading = MutableStateFlow<Boolean>(false)
        val isLoading = _isLoading.asStateFlow()
        var toastMessage = MutableLiveData<String>("")

        fun postComment(content: String) {
            viewModelScope.launch {
                if (content.isBlank()) {
                    toastMessage.value = "댓글을 입력해주세요."
                } else {
                    commentRepository.submitComment(
                        CommentSubmitParam(
                            postId = postId.value ?: 0,
                            content = content ?: "",
                        ),
                    ).safeApiCall(
                        _isLoading,
                        {
                            toastMessage.value = "댓글 작성에 성공했습니다."
                            getComment()
                        },
                        {
                            toastMessage.value = "댓글 작성에 실패했습니다."
                        },
                    )
                }
            }
        }

        fun getComment() {
            commentRepository.getCommentList(postId.value ?: 0)
                .safeApiCall(
                    _isLoading,
                    { comments ->
                        commentState.value =
                            CommentState(
                                commentList = comments,
                            )
                    },
                    {
                        toastMessage.value = "댓글 불러오기에 실패했습니다."
                    },
                )
        }

        fun deleteComment(commentId: Int) {
            commentRepository.deleteComment(commentId)
                .safeApiCall(
                    _isLoading,
                    {
                        toastMessage.value = "댓글 삭제에 성공했습니다."
                        commentState.value.commentList?.let { list ->
                            commentState.value =
                                CommentState(
                                    commentList =
                                        list.filter { comment ->
                                            comment.commentId.toInt() != commentId
                                        },
                                )
                        }
                    },
                    {
                        toastMessage.value = "댓글 삭제에 실패했습니다."
                    },
                )
        }

        fun updateComment(
            commentId: Int,
            content: String,
        ) {
            commentRepository.updateComment(CommentUpdateParam(commentId, content)).safeApiCall(
                _isLoading,
                {
                    getComment()
                    toastMessage.value = "댓글 수정에 성공했습니다."
                },
                {
                    getComment()
                    toastMessage.value = "댓글 수정에 실패했습니다."
                },
            )
        }
    }
