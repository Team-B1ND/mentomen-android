package kr.hs.dgsw.mentomenv2.feature.detail.comment

import dagger.hilt.android.lifecycle.HiltViewModel
import kr.hs.dgsw.mentomenv2.base.BaseViewModel
import kr.hs.dgsw.mentomenv2.domain.repository.CommentRepository
import javax.inject.Inject

@HiltViewModel
class CommentSheetViewModel @Inject constructor(
    private val commentRepository: CommentRepository
): BaseViewModel() {
    fun editComment() {

    }
    fun deleteComment() {

    }
    fun cancel() {
        finish()
    }
}