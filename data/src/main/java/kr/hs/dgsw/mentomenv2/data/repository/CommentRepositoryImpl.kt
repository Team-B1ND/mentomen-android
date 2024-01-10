package kr.hs.dgsw.mentomenv2.data.repository

import kotlinx.coroutines.flow.Flow
import kr.hs.dgsw.mentomenv2.data.datasource.CommentDataSource
import kr.hs.dgsw.mentomenv2.data.repository.base.BaseRepositoryImpl
import kr.hs.dgsw.mentomenv2.data.request.CommentSubmitRequest
import kr.hs.dgsw.mentomenv2.data.request.CommentUpdateRequest
import kr.hs.dgsw.mentomenv2.domain.model.Comment
import kr.hs.dgsw.mentomenv2.domain.params.CommentSubmitParam
import kr.hs.dgsw.mentomenv2.domain.params.CommentUpdateParam
import kr.hs.dgsw.mentomenv2.domain.repository.CommentRepository
import kr.hs.dgsw.mentomenv2.domain.util.Result
import javax.inject.Inject

class CommentRepositoryImpl
@Inject
constructor(
    private val commentDataSource: CommentDataSource,
) : BaseRepositoryImpl(), CommentRepository {
    override fun submitComment(commentSubmitParam: CommentSubmitParam): Flow<Result<Unit>> =
        execute {
            commentDataSource.submitComment(
                CommentSubmitRequest(
                    commentSubmitParam.content,
                    commentSubmitParam.postId
                )
            )
        }

    override fun updateComment(commentUpdateParam: CommentUpdateParam): Flow<Result<Unit>> =
        execute {
            commentDataSource.updateComment(
                CommentUpdateRequest(
                    commentUpdateParam.commentId,
                    commentUpdateParam.content
                )
            )
        }

    override fun deleteComment(commentId: Int): Flow<Result<Unit>> =
        execute {
            commentDataSource.deleteComment(commentId)
        }

    override fun getCommentList(postId: Int): Flow<Result<List<Comment>>> =
        execute {
            commentDataSource.getCommentList(postId)
        }

}
