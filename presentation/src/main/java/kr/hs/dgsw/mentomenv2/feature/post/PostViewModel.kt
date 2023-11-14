package kr.hs.dgsw.mentomenv2.feature.post

import androidx.lifecycle.MutableLiveData
import kr.hs.dgsw.mentomenv2.base.BaseViewModel
import okhttp3.MultipartBody

class PostViewModel : BaseViewModel() {
    val content = MutableLiveData<String>("")
    val tagState = MutableLiveData<String>("ALL")
    val imgFile = MutableLiveData<ArrayList<MultipartBody.Part?>>(arrayListOf())
    fun onClickDesignBtn() {
        tagState.value = "DESIGN"
    }

    fun onClickWebBtn() {
        tagState.value = "WEB"
    }

    fun onClickAndroidBtn() {
        tagState.value = "ANDROID"
    }

    fun onClickServerBtn() {
        tagState.value = "SERVER"
    }

    fun onClickIOSBtn() {
        tagState.value = "IOS"
    }

    fun onClickImage() {
        viewEvent(ON_CLICK_IMAGE)
    }

    fun onClickConfirm() {
        viewEvent(ON_CLICK_CONFIRM)
    }

    companion object {
        const val ON_CLICK_IMAGE = 0
        const val ON_CLICK_CONFIRM = 1
    }
}
