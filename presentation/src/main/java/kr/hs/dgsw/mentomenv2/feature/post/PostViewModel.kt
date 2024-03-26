package kr.hs.dgsw.mentomenv2.feature.post

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kr.hs.dgsw.mentomenv2.base.BaseViewModel
import kr.hs.dgsw.mentomenv2.domain.model.ImgUrl
import kr.hs.dgsw.mentomenv2.domain.params.PostSubmitParam
import kr.hs.dgsw.mentomenv2.domain.usecase.file.PostFileUseCase
import kr.hs.dgsw.mentomenv2.domain.usecase.post.GetPostByIdUseCase
import kr.hs.dgsw.mentomenv2.domain.usecase.post.PostSubmitUseCase
import kr.hs.dgsw.mentomenv2.domain.util.Log
import okhttp3.MultipartBody
import javax.inject.Inject

@HiltViewModel
class PostViewModel
@Inject
constructor(
    private val submitUseCase: PostSubmitUseCase,
    private val postFileUseCase: PostFileUseCase,
    private val getPostByIdUseCase: GetPostByIdUseCase
) : BaseViewModel() {
    val content = MutableLiveData<String>("")
    val tagState = MutableLiveData<String>("ALL")
    val imgFile = MutableLiveData<ArrayList<MultipartBody.Part>>(arrayListOf())
    val imgUrl = MutableLiveData<List<ImgUrl?>>(emptyList())
    val postMessage = MutableStateFlow<String>("")
    private val _isLoading = MutableStateFlow<Boolean>(false)
    val isLoading = _isLoading.asStateFlow()
    var submitMessage = MutableSharedFlow<String>()

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
        applyEvent("이미지 업로드 중입니다.")
        Log.d("loadImage: ", "imgFile : ${imgFile.value}")
        postFileUseCase(imgFile.value!!).safeApiCall(
            _isLoading,
            successAction = {
                Log.d("loadImage: success", "result : $it")
                imgUrl.value = it

                submitUseCase(
                    PostSubmitParam(
                        content.value!!,
                        imgUrl.value!!,
                        tagState.value!!,
                    ),
                ).safeApiCall(
                    _isLoading,
                    successAction = {
                        applyEvent("게시글 등록에 성공했습니다.")
                    },
                    errorAction = {
                        applyEvent(it.toString())
                    },
                )
            },
            errorAction = {
                applyEvent(it.toString())
                Log.d("loadImage: fail", "result : $it")
                imgUrl.value = emptyList()
            },
        )
    }

    private fun applyEvent(message: String) {
        viewModelScope.launch {
            submitMessage.emit(message)
            viewEvent(submitMessage)
        }
    }

    fun submitPost() {
        if (!imgFile.value.isNullOrEmpty()) {
            loadImage()
        } else {
            submitUseCase(
                PostSubmitParam(
                    content.value!!,
                    imgUrl.value!!,
                    tagState.value!!,
                ),
            ).safeApiCall(
                _isLoading,
                successAction = {
                    applyEvent("게시글 등록에 성공했습니다.")
                },
                errorAction = {
                    applyEvent(it.toString())
                },
            )
        }
    }

    fun getPostInfo(postId: Int) {
        getPostByIdUseCase.invoke(postId).safeApiCall(
            _isLoading,
            successAction = { post ->
                postMessage.value = post?.content ?: ""
                tagState.value = post?.tag
                imgUrl.value = post?.imgUrls?.map { ImgUrl(it) } ?: emptyList()
            },
            errorAction = {
                applyEvent(it.toString())
            }
        )
    }

    companion object {
        const val ON_CLICK_IMAGE = 0
        const val ON_CLICK_SUBMIT = 1
    }
}
