package kr.hs.dgsw.mentomenv2.feature.post

import androidx.lifecycle.MutableLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import kr.hs.dgsw.mentomenv2.base.BaseViewModel
import kr.hs.dgsw.mentomenv2.domain.params.PostSubmitParam
import kr.hs.dgsw.mentomenv2.domain.repository.DataStoreRepository
import kr.hs.dgsw.mentomenv2.domain.usecase.post.PostSubmitUseCase
import kr.hs.dgsw.smartschool.dodamdodam.dauth.model.response.UserResponse
import okhttp3.MultipartBody
import javax.inject.Inject

@HiltViewModel
class PostViewModel @Inject constructor(
    private val submitUseCase: PostSubmitUseCase,
    private val dataStoreRepository: DataStoreRepository
) : BaseViewModel() {
    val content = MutableLiveData<String>("")
    val tagState = MutableLiveData<String>("ALL")
    val imgFile = MutableLiveData<ArrayList<MultipartBody.Part?>>(arrayListOf())
    val imgUrl = MutableLiveData<List<String?>>(emptyList())
    val token = MutableLiveData<String>("")
    val isTokenLoading = MutableLiveData<Boolean>(false)
    val isPostLoading = MutableLiveData<Boolean>(false)

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

    fun onClickSubmit() {
        viewEvent(ON_CLICK_SUBMIT)
    }

    fun submitPost(userInfo: UserResponse) {
        submitUseCase(PostSubmitParam(content.value!!,imgUrl.value!!,tagState.value!!)).safeApiCall(
            isPostLoading,
            successAction =  {},
            errorAction = {}
        )
    }

    fun getToken() {
        dataStoreRepository.getToken().safeApiCall(
            isTokenLoading,
            successAction =  {
                token.value = it?.accessToken
            },
            errorAction =  {
                token.value = ""
            }
        )
    }

    companion object {
        const val ON_CLICK_IMAGE = 0
        const val ON_CLICK_SUBMIT = 1
    }
}
