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
import kr.hs.dgsw.mentomenv2.domain.params.CommentUpdateParam
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
    val isLoading = MutableLiveData<Boolean>(false)
    var errorMessage = MutableLiveData<String>("")
    fun postComment() {
        viewModelScope.launch(Dispatchers.IO) {
            if (commentContent.value.isNullOrBlank()) {
                errorMessage.value = "댓글을 입력해주세요."
            } else {
                commentRepository.submitComment(
                    CommentSubmitParam(
                        postId = postId.value ?: 0,
                        content = commentContent.value ?: "",
                    ),
                ).safeApiCall(
                    isLoading,
                    {
                        Log.d( "postComment: ", "댓글 작성 성공")
                        viewEvent(UPLOAD_COMMENT)
                        commentContent.value = ""
                        getComment()
                    },
                    {
                        errorMessage.value = "댓글 작성에 실패했습니다."
                    }
                )
            }
        }
    }

    fun getComment() {
        commentRepository.getCommentList(postId.value ?: 0)
            .safeApiCall(
                isLoading,
                { comments ->
                    commentState.value =
                        CommentState(
                            commentList = comments,
                        )
                },
                {
                    errorMessage.value = "댓글 불러오기에 실패했습니다."
                }
            )
    }

    fun deleteComment(commentId: Int) {
        commentRepository.deleteComment(commentId)
            .safeApiCall(
                isLoading,
                {
                    viewEvent(DELETE_COMMENT)
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
                    errorMessage.value = "댓글 삭제에 실패했습니다."
                }
            )
    }

    fun updateComment(
        commentId: Int,
        content: String,
    ) {
        commentRepository.updateComment(CommentUpdateParam(commentId, content)).safeApiCall(
            isLoading,
            {
                viewEvent(UPDATE_COMMENT)
            },
            {
                errorMessage.value = "댓글 삭제에 실패했습니다."
            }
        )
    }

    companion object Event {
        const val DELETE_COMMENT = 1
        const val UPDATE_COMMENT = 2
        const val UPLOAD_COMMENT = 3
    }
}
