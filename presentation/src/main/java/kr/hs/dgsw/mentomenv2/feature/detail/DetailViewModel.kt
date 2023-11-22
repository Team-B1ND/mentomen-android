package kr.hs.dgsw.mentomenv2.feature.detail

import androidx.lifecycle.MutableLiveData
import kr.hs.dgsw.mentomenv2.base.BaseViewModel
import kr.hs.dgsw.mentomenv2.domain.model.StdInfo
import org.w3c.dom.Comment

class DetailViewModel: BaseViewModel() {

    val itemList = MutableLiveData<List<Comment>>()
    val userId = MutableLiveData<Int>()
    val author = MutableLiveData<Int>()
    val postId = MutableLiveData<Int>()
    val tag = MutableLiveData<String>()
    val content = MutableLiveData<String>()
    val imgUrl = MutableLiveData<List<String?>>()
    val createDateTime = MutableLiveData<String>("2023-11-06T14:28:51.528245")
    val stdInfo = MutableLiveData<StdInfo>(StdInfo(2,4,6))
    val profileUrl = MutableLiveData<String>()
    val userName = MutableLiveData<String>()
    val commentContent = MutableLiveData<String>()
    val profileImage = MutableLiveData<String>()

    fun postComment() {

    }
}
