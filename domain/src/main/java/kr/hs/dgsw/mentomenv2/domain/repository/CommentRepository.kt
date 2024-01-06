package kr.hs.dgsw.mentomenv2.domain.repository

import kotlinx.coroutines.flow.Flow
import kr.hs.dgsw.mentomenv2.domain.model.Comment
import kr.hs.dgsw.mentomenv2.domain.params.CommentSubmitParam
import kr.hs.dgsw.mentomenv2.domain.params.CommentUpdateParam
import kr.hs.dgsw.mentomenv2.domain.util.Result

interface CommentRepository {
    fun submitComment(commentSubmitParam: CommentSubmitParam)
    fun updateComment(commentUpdateParam: CommentUpdateParam)
    fun deleteComment(commentId: Int)
    fun getCommentList(postId: Int): Flow<Result<List<Comment>>>
}