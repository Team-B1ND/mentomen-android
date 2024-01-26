package kr.hs.dgsw.mentomenv2.feature.detail.comment

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kr.hs.dgsw.mentomenv2.base.BaseViewModel
import kr.hs.dgsw.mentomenv2.domain.params.CommentSubmitParam
import kr.hs.dgsw.mentomenv2.domain.repository.CommentRepository
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
        val commentContent = MutableLiveData<String>()

        fun postComment() {
            viewModelScope.launch(Dispatchers.IO) {
                if (commentContent.value.isNullOrBlank()) {
                    Log.d("postComment: ", "postComment is empty")
                    commentState.value =
                        CommentState(
                            error = "내용을 입력해주세요.",
                        )
                } else {
                    commentRepository.submitComment(
                        CommentSubmitParam(
                            postId = postId.value ?: 0,
                            content = commentContent.value ?: "",
                        ),
                    ).safeApiCall(
                        null,
                        {
                            commentContent.value = ""
                            getComment()
                        },
                        {
                            commentState.value =
                                CommentState(
                                    error = it.toString(),
                                )
                            getComment()
                        },
                    )
                }
            }
        }

        fun getComment() {
            commentRepository.getCommentList(postId.value ?: 0)
                .safeApiCall(
                    null,
                    { comments ->
                        Log.d("getComment: ", "getComment: $comments")
                        commentState.value =
                            CommentState(
                                commentList = comments,
                            )
                    },
                    {
                        commentState.value =
                            CommentState(
                                error = "finish",
                            )
                    },
                )
        }

        fun deleteComment(commentId: Int) {
            commentRepository.deleteComment(commentId)
                .safeApiCall(
                    null,
                    {
                        commentState.value.commentList?.let { list ->
                            commentState.value =
                                CommentState(
                                    commentList =
                                        list.filter { comment ->
                                            comment.commentId.toInt() != commentId
                                        },
                                    error = "댓글 삭제 성공",
                                )
                        }
                    },
                    {
                        commentState.value =
                            CommentState(
                                error = it.toString(),
                            )
                        getComment()
                    },
                )
        }

        fun updateComment(
            commentId: Int,
            content: String,
        ) {
        }
    }
