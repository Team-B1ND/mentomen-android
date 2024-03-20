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
    var toastMessage = MutableLiveData<String>("")
    fun postComment() {
        viewModelScope.launch(Dispatchers.IO) {
            if (commentContent.value.isNullOrBlank()) {
                toastMessage.value = "댓글을 입력해주세요."
            } else {
                commentRepository.submitComment(
                    CommentSubmitParam(
                        postId = postId.value ?: 0,
                        content = commentContent.value ?: "",
                    ),
                ).safeApiCall(
                    isLoading,
                    {
                        toastMessage.value = "댓글 작성에 성공했습니다."
                        Log.d( "postComment: ", "댓글 작성 성공")
                        commentContent.value = ""
                        getComment()
                    },
                    {
                        toastMessage.value = "댓글 작성에 실패했습니다."
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
                    toastMessage.value = "댓글 불러오기에 실패했습니다."
                }
            )
    }

    fun deleteComment(commentId: Int) {
        commentRepository.deleteComment(commentId)
            .safeApiCall(
                isLoading,
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
                getComment()
                toastMessage.value = "댓글 수정에 성공했습니다."
            },
            {
                getComment()
                toastMessage.value = "댓글 수정에 실패했습니다."
            }
        )
    }
}
