package kr.hs.dgsw.mentomenv2.data.datasource.remote

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kr.hs.dgsw.mentomenv2.data.remote.CommentDataSource
import kr.hs.dgsw.mentomenv2.data.request.CommentSubmitRequest
import kr.hs.dgsw.mentomenv2.data.request.CommentUpdateRequest
import kr.hs.dgsw.mentomenv2.data.service.CommentService
import kr.hs.dgsw.mentomenv2.domain.model.Comment
import javax.inject.Inject

class CommentDataSourceImpl
@Inject
constructor(
    private val api: CommentService,
) : CommentDataSource {
    override fun getCommentList(postId: Int): Flow<List<Comment>> {
        return flow {
            emit(api.getCommentList(postId).data)
        }
    }

    override fun submitComment(commentSubmitRequest: CommentSubmitRequest): Flow<Unit> {
        return flow {
            emit(api.submitComment(commentSubmitRequest).data)
        }
    }

    override fun updateComment(commentUpdateRequest: CommentUpdateRequest): Flow<Unit> {
        return flow {
            emit(api.updateComment(commentUpdateRequest).data)
        }
    }

    override fun deleteComment(commentId: Int): Flow<Unit> {
        return flow {
            emit(api.deleteComment(commentId).data)
        }
    }
}
