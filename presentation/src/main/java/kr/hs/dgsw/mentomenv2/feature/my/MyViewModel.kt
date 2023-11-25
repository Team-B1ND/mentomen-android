package kr.hs.dgsw.mentomenv2.feature.my

import androidx.lifecycle.MutableLiveData
import kr.hs.dgsw.mentomenv2.base.BaseViewModel
import kr.hs.dgsw.mentomenv2.domain.model.Post
import kr.hs.dgsw.mentomenv2.domain.model.StdInfo

class MyViewModel : BaseViewModel() {
    val stdInfo = StdInfo(grade = 2, room = 4, number = 6)
    val post = MutableLiveData<Post>(
        Post(
            author = 1,
            content = "test내용ㅣ니다",
            imgUrls = listOf("https://flexible.img.hani.co.kr/flexible/normal/640/427/imgdb/original/2023/1106/20231106503357.jpg"),
            createDateTime = "2023-11-06T14:28:51.528245",
            postId = 1,
            profileUrl = "https://dodam.kr.object.ncloudstorage.com/dodam/357d39e0-4c1c-4c31-81c8-f791c14566dfDODAM_FILE_280255852.jpg",
            stdInfo = stdInfo,
            tag = "DESIGN",
            updateDateTime = "2023-11-06T14:28:51.528245",
            updateStatus = "NOT_UPDATE",
            userName = "도현욱"
        )
    )
}
