package kr.hs.dgsw.mentomenv2.feature.my

import androidx.lifecycle.MutableLiveData
import kr.hs.dgsw.mentomenv2.base.BaseViewModel
import kr.hs.dgsw.mentomenv2.domain.model.Post
import kr.hs.dgsw.mentomenv2.domain.model.StdInfo

class MyViewModel: BaseViewModel() {
    val noticeStatus = MutableLiveData("NONE")
    val email = MutableLiveData<String>()
    val name = MutableLiveData<String>()
    val stdInfo = MutableLiveData<StdInfo>()
    val profileImage = MutableLiveData<String>()
    val itemList = MutableLiveData<List<Post>>()

    init {
        stdInfo.value = StdInfo(2,4,6)
        email.value = ""
        name.value = "도현욱"
        profileImage.value = "https://flexible.img.hani.co.kr/flexible/normal/847/635/imgdb/original/2023/1027/20231027502376.jpg"
        itemList.value = listOf(Post(123, "제가 서버를 취미로 하고 있는 디자인 담당인데 이 디자인 한 번 평가해주실 수 있을까요?", listOf("https://flexible.img.hani.co.kr/flexible/normal/847/635/imgdb/original/2023/1027/20231027502376.jpg"), "",123,"",
            StdInfo(3,2,1), "", "4시간 전", "", "도현욱"))
    }
}