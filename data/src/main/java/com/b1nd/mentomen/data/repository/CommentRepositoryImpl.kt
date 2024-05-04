package com.b1nd.mentomen.data.repository

import com.b1nd.mentomen.domain.model.Comment
import com.b1nd.mentomen.data.datasource.CommentDataSource
import com.b1nd.mentomen.data.repository.base.BaseRepositoryImpl
import com.b1nd.mentomen.data.request.CommentSubmitRequest
import com.b1nd.mentomen.data.request.CommentUpdateRequest
import com.b1nd.mentomen.domain.params.CommentSubmitParam
import com.b1nd.mentomen.domain.params.CommentUpdateParam
import com.b1nd.mentomen.domain.repository.CommentRepository
import com.b1nd.mentomen.domain.util.Result
import kotlinx.coroutines.flow.Flow
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
                        commentSubmitParam.postId,
                    ),
                )
            }

        override fun updateComment(commentUpdateParam: CommentUpdateParam): Flow<Result<Unit>> =
            execute {
                commentDataSource.updateComment(
                    CommentUpdateRequest(
                        commentUpdateParam.commentId,
                        commentUpdateParam.content,
                    ),
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
