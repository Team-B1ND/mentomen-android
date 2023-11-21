package kr.hs.dgsw.mentomenv2.feature.my

import androidx.lifecycle.MutableLiveData
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
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
        profileImage.value = "https://dodam.kr.object.ncloudstorage.com/dodam/357d39e0-4c1c-4c31-81c8-f791c14566dfDODAM_FILE_280255852.jpg"
        itemList.value = listOf(Post(1, "test입니다", listOf("https://flexible.img.hani.co.kr/flexible/normal/640/427/imgdb/original/2023/1106/20231106503357.jpg"), "2023-11-06T14:28:51.528245",123,"https://dodam.kr.object.ncloudstorage.com/dodam/357d39e0-4c1c-4c31-81c8-f791c14566dfDODAM_FILE_280255852.jpg",
            StdInfo(2,4,6), "DESIGN", "2023-11-06T14:28:51.528245", "NOT_UPDATE", "도현욱"))
    }
}
