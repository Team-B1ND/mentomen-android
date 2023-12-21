package kr.hs.dgsw.mentomenv2.feature.post

import android.Manifest
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.gun0912.tedpermission.coroutine.TedPermission
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kr.hs.dgsw.mentomenv2.base.BaseViewModel
import kr.hs.dgsw.mentomenv2.domain.model.ImgUrl
import kr.hs.dgsw.mentomenv2.domain.params.PostSubmitParam
import kr.hs.dgsw.mentomenv2.domain.usecase.file.PostFileUseCase
import kr.hs.dgsw.mentomenv2.domain.usecase.post.PostSubmitUseCase
import okhttp3.MultipartBody
import javax.inject.Inject

@HiltViewModel
class PostViewModel @Inject constructor(
    private val submitUseCase: PostSubmitUseCase,
    private val postFileUseCase: PostFileUseCase
) : BaseViewModel() {
    val content = MutableLiveData<String>("")
    val tagState = MutableLiveData<String>("ALL")
    val imgFile = MutableLiveData<ArrayList<MultipartBody.Part>>(arrayListOf())
    val imgUrl = MutableLiveData<List<ImgUrl?>>(emptyList())
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

    private fun loadImage() {
        Log.d("loadImage: ", "imgFile : ${imgFile.value}")
        postFileUseCase.invoke(imgFile.value!!).safeApiCall(
            isPostLoading,
            successAction = {
                Log.d("loadImage: success", "result : $it")
                imgUrl.value = it
            },
            errorAction = {
                Log.d("loadImage: fail", "result : $it")
                imgUrl.value = emptyList()
            }
        )
    }

    private fun applyError(message: String) {
        SUBMIT_MESSAGE = message
        viewEvent(SUBMIT_MESSAGE)
    }

    fun submitPost() {
        if (!imgFile.value.isNullOrEmpty()) {
            imgFile.value?.forEach { part ->
                // MultipartBody.Part의 내용 출력
                Log.d(
                    "MultipartBody.Part",
                    "Content-Disposition: ${part.headers!!["Content-Disposition"]}"
                )
                Log.d("MultipartBody.Part", "Content-Type: ${part.headers!!["Content-Type"]}")
                Log.d("MultipartBody.Part", "Body: ${part.body}")
            }
            loadImage()
        }
        Log.d(
            "submitPost: ",
            "content : ${content.value} images : ${imgUrl.value} tag : ${tagState.value}"
        )
//        submitUseCase(
//            PostSubmitParam(
//                content.value!!,
//                imgUrl.value!!,
//                tagState.value!!
//            )
//        ).safeApiCall(
//            isPostLoading,
//            successAction = {
//                applyError("게시글 등록에 성공했습니다.")
//            },
//            errorAction = {
//                applyError(it.toString())
//            }
//        )
    }

    companion object {
        const val ON_CLICK_IMAGE = 0
        const val ON_CLICK_SUBMIT = 1
        var SUBMIT_MESSAGE = ""
    }
}
