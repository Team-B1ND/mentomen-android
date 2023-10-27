package kr.hs.dgsw.mentomenv2.feature.main

import androidx.lifecycle.MutableLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kr.hs.dgsw.mentomenv2.base.BaseViewModel
import kr.hs.dgsw.mentomenv2.domain.model.Post
import kr.hs.dgsw.mentomenv2.domain.model.StdInfo
import kr.hs.dgsw.mentomenv2.state.PostState
import javax.inject.Inject

@HiltViewModel
class HomeFragmentViewModel @Inject constructor() : BaseViewModel() {
    val tagState = MutableStateFlow<PostState>(PostState.ALL)
    val itemList = MutableLiveData<List<Post>>(listOf(Post(123, "제가 서버를 취미로 하고 있는 디자인 담당인데 이 디자인 한 번 평가해주실 수 있을까요? 1234567890123456789012345678901234567890", listOf("https://flexible.img.hani.co.kr/flexible/normal/847/635/imgdb/original/2023/1027/20231027502376.jpg"), "",123,"",
        StdInfo(3,2,1), "", "4시간 전", "", "도현욱"),
        Post(123, "제가 서버를 취미로 하고 있는 디자인 담당인데 이 디자인 한 번 평가해주실 수 있을까요? 1234567890123456789012345678901234567890", listOf("https://flexible.img.hani.co.kr/flexible/normal/847/635/imgdb/original/2023/1027/20231027502376.jpg"), "",123,"",
            StdInfo(3,2,1), "", "4시간 전", "", "도현욱"),
        Post(123, "제가 서버를 취미로 하고 있는 디자인 담당인데 이 디자인 한 번 평가해주실 수 있을까요? 1234567890123456789012345678901234567890", listOf("https://flexible.img.hani.co.kr/flexible/normal/847/635/imgdb/original/2023/1027/20231027502376.jpg"), "",123,"",
            StdInfo(3,2,1), "", "4시간 전", "", "도현욱"),
        Post(123, "제가 서버를 취미로 하고 있는 디자인 담당인데 이 디자인 한 번 평가해주실 수 있을까요? 1234567890123456789012345678901234567890", listOf("https://flexible.img.hani.co.kr/flexible/normal/847/635/imgdb/original/2023/1027/20231027502376.jpg"), "",123,"",
            StdInfo(3,2,1), "", "4시간 전", "", "도현욱"),
        Post(123, "제가 서버를 취미로 하고 있는 디자인 담당인데 이 디자인 한 번 평가해주실 수 있을까요? 1234567890123456789012345678901234567890", listOf("https://flexible.img.hani.co.kr/flexible/normal/847/635/imgdb/original/2023/1027/20231027502376.jpg"), "",123,"",
            StdInfo(3,2,1), "", "4시간 전", "", "도현욱"),
        Post(123, "제가 서버를 취미로 하고 있는 디자인 담당인데 이 디자인 한 번 평가해주실 수 있을까요? 1234567890123456789012345678901234567890", listOf("https://flexible.img.hani.co.kr/flexible/normal/847/635/imgdb/original/2023/1027/20231027502376.jpg"), "",123,"",
            StdInfo(3,2,1), "", "4시간 전", "", "도현욱"),
        Post(123, "제가 서버를 취미로 하고 있는 디자인 담당인데 이 디자인 한 번 평가해주실 수 있을까요? 1234567890123456789012345678901234567890", listOf("https://flexible.img.hani.co.kr/flexible/normal/847/635/imgdb/original/2023/1027/20231027502376.jpg"), "",123,"",
            StdInfo(3,2,1), "", "4시간 전", "", "도현욱"),
        Post(123, "제가 서버를 취미로 하고 있는 디자인 담당인데 이 디자인 한 번 평가해주실 수 있을까요? 1234567890123456789012345678901234567890", listOf("https://flexible.img.hani.co.kr/flexible/normal/847/635/imgdb/original/2023/1027/20231027502376.jpg"), "",123,"",
            StdInfo(3,2,1), "", "4시간 전", "", "도현욱"),
        Post(123, "제가 서버를 취미로 하고 있는 디자인 담당인데 이 디자인 한 번 평가해주실 수 있을까요? 1234567890123456789012345678901234567890", listOf("https://flexible.img.hani.co.kr/flexible/normal/847/635/imgdb/original/2023/1027/20231027502376.jpg"), "",123,"",
            StdInfo(3,2,1), "", "4시간 전", "", "도현욱"),
        Post(123, "제가 서버를 취미로 하고 있는 디자인 담당인데 이 디자인 한 번 평가해주실 수 있을까요? 1234567890123456789012345678901234567890", listOf("https://flexible.img.hani.co.kr/flexible/normal/847/635/imgdb/original/2023/1027/20231027502376.jpg"), "",123,"",
            StdInfo(3,2,1), "", "4시간 전", "", "도현욱"),
        Post(123, "제가 서버를 취미로 하고 있는 디자인 담당인데 이 디자인 한 번 평가해주실 수 있을까요? 1234567890123456789012345678901234567890", listOf("https://flexible.img.hani.co.kr/flexible/normal/847/635/imgdb/original/2023/1027/20231027502376.jpg"), "",123,"",
            StdInfo(3,2,1), "", "4시간 전", "", "도현욱"),
        Post(123, "제가 서버를 취미로 하고 있는 디자인 담당인데 이 디자인 한 번 평가해주실 수 있을까요? 1234567890123456789012345678901234567890", listOf("https://flexible.img.hani.co.kr/flexible/normal/847/635/imgdb/original/2023/1027/20231027502376.jpg"), "",123,"",
            StdInfo(3,2,1), "", "4시간 전", "", "도현욱"),
        Post(123, "제가 서버를 취미로 하고 있는 디자인 담당인데 이 디자인 한 번 평가해주실 수 있을까요? 1234567890123456789012345678901234567890", listOf("https://flexible.img.hani.co.kr/flexible/normal/847/635/imgdb/original/2023/1027/20231027502376.jpg"), "",123,"",
            StdInfo(3,2,1), "", "4시간 전", "", "도현욱"),
        Post(123, "제가 서버를 취미로 하고 있는 디자인 담당인데 이 디자인 한 번 평가해주실 수 있을까요? 1234567890123456789012345678901234567890", listOf("https://flexible.img.hani.co.kr/flexible/normal/847/635/imgdb/original/2023/1027/20231027502376.jpg"), "",123,"",
            StdInfo(3,2,1), "", "4시간 전", "", "도현욱"),
        Post(123, "제가 서버를 취미로 하고 있는 디자인 담당인데 이 디자인 한 번 평가해주실 수 있을까요? 1234567890123456789012345678901234567890", listOf("https://flexible.img.hani.co.kr/flexible/normal/847/635/imgdb/original/2023/1027/20231027502376.jpg"), "",123,"",
            StdInfo(3,2,1), "", "4시간 전", "", "도현욱")))

    init {
        getAllPost()
    }

    fun getAllPost() {

    }

    fun onClickDesignBtn() {

    }

    fun onClickWebBtn() {

    }

    fun onClickAndroidBtn() {

    }

    fun onClickServerBtn() {

    }

    fun onClickIOSBtn() {

    }
}
