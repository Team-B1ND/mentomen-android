package kr.hs.dgsw.mentomenv2.feature.detail

import androidx.lifecycle.MutableLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import kr.hs.dgsw.mentomenv2.base.BaseViewModel
import kr.hs.dgsw.mentomenv2.domain.model.Comment
import kr.hs.dgsw.mentomenv2.domain.model.StdInfo
import kr.hs.dgsw.mentomenv2.domain.usecase.my.GetMyInfoUseCase
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val getMyInfoUseCase: GetMyInfoUseCase
) : BaseViewModel() {
    val itemList = MutableLiveData<List<Comment>>()
    val myUserId = MutableLiveData<Int>()
    val author = MutableLiveData<Int>()

    val tag = MutableLiveData<String>()
    val content = MutableLiveData<String>()
    val imgUrl = MutableLiveData<List<String?>>()
    val createDateTime = MutableLiveData<String>("2023-11-06T14:28:51.528245")
    val stdInfo = MutableLiveData<StdInfo>(StdInfo(2, 4, 6))
    val profileUrl = MutableLiveData<String>()
    val userName = MutableLiveData<String>()
    val profileImage = MutableLiveData<String>()

    fun getUserInfo() {
        getMyInfoUseCase.invoke().safeApiCall(
            null,
            {
                myUserId.value = it?.userId
                profileImage.value = it?.profileImage
            },
            {
                //401이라면? 로그인 되어 있지 않다는 상태 저장 후 댓글 작성 editText 클릭 시 로그인창 띄우기
            }
        )
    }
}
