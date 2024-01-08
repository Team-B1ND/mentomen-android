package kr.hs.dgsw.mentomenv2.feature.detail

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kr.hs.dgsw.mentomenv2.base.BaseViewModel
import kr.hs.dgsw.mentomenv2.domain.model.Comment
import kr.hs.dgsw.mentomenv2.domain.model.StdInfo
import kr.hs.dgsw.mentomenv2.domain.params.CommentSubmitParam
import kr.hs.dgsw.mentomenv2.domain.repository.CommentRepository
import kr.hs.dgsw.mentomenv2.state.CommentState
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val commentRepository: CommentRepository
) : BaseViewModel() {
    val itemList = MutableLiveData<List<Comment>>()
    val userId = MutableLiveData<Int>()
    val author = MutableLiveData<Int>()
    val postId = MutableLiveData<Int>()
    val tag = MutableLiveData<String>()
    val content = MutableLiveData<String>()
    val imgUrl = MutableLiveData<List<String?>>()
    val createDateTime = MutableLiveData<String>("2023-11-06T14:28:51.528245")
    val stdInfo = MutableLiveData<StdInfo>(StdInfo(2, 4, 6))
    val profileUrl = MutableLiveData<String>()
    val userName = MutableLiveData<String>()
    val commentContent = MutableLiveData<String>()
    val profileImage = MutableLiveData<String>()
    val commentState = MutableStateFlow<CommentState>(CommentState())

    fun postComment() {
        viewModelScope.launch(Dispatchers.IO) {
            if (commentContent.value.isNullOrBlank()) {
                Log.d("postComment: ", "postComment is empty")
                commentState.value = CommentState(
                    error = "내용을 입력해주세요.",
                )
            } else {
                commentRepository.submitComment(
                    CommentSubmitParam(
                        postId = postId.value ?: 0,
                        content = commentContent.value ?: "",
                    )
                ).safeApiCall(
                    null,
                    {
                        commentState.value = CommentState(
                            error = "댓글 작성 성공"
                        )
                        commentRepository.getCommentList(postId.value ?: 0).safeApiCall(null,
                            { comments ->
                                commentState.value = CommentState(
                                    commentList = comments,
                                )
                            },
                            {
                                commentState.value = CommentState(
                                    error = it.toString(),
                                )
                            })
                        Log.d("postComment: ", "postComment 성공")
                    },
                    {
                        commentState.value = CommentState(
                            error = it.toString()
                        )
                    }
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
                    commentState.value = CommentState(
                        commentList = comments,
                    )
                },
                {
                    commentState.value = CommentState(
                        error = it.toString(),
                    )
                }
            )
    }
}
