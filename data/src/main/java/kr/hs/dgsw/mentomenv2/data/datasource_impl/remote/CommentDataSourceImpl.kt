package kr.hs.dgsw.mentomenv2.data.datasource_impl.remote

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kr.hs.dgsw.mentomenv2.domain.exception.MenToMenException
import kr.hs.dgsw.mentomenv2.data.datasource.CommentDataSource
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
            val response = api.getCommentList(postId).execute()

            if (response.isSuccessful) {
                emit(response.body()?.data ?: emptyList())
            } else {
                throw MenToMenException("댓글 목록을 불러오는데 실패했습니다.")
            }
        }.flowOn(Dispatchers.IO)
    }

    override fun submitComment(commentSubmitRequest: CommentSubmitRequest): Flow<Unit> {
        return flow {
            val response = api.submitComment(commentSubmitRequest).execute()

            if (response.isSuccessful) {
                emit(response.body()?.data ?: Unit)
            } else {
                throw MenToMenException("댓글을 작성하는데 실패했습니다.")
            }
        }.flowOn(Dispatchers.IO)
    }

    override fun updateComment(commentUpdateRequest: CommentUpdateRequest): Flow<Unit> {
        return flow {
            val response = api.updateComment(commentUpdateRequest).execute()

            if (response.isSuccessful) {
                emit(response.body()?.data ?: Unit)
            } else {
                throw MenToMenException("댓글을 수정하는데 실패했습니다.")
            }
        }.flowOn(Dispatchers.IO)
    }

    override fun deleteComment(commentId: Int): Flow<Unit> {
        return flow {
            val response = api.deleteComment(commentId).execute()

            if (response.isSuccessful) {
                emit(response.body()?.data ?: Unit)
            } else {
                throw MenToMenException("댓글을 삭제하는데 실패했습니다.")
            }
        }.flowOn(Dispatchers.IO)
    }
}
