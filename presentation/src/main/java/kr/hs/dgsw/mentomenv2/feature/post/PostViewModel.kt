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
import kr.hs.dgsw.mentomenv2.domain.params.PostEditParam
import kr.hs.dgsw.mentomenv2.domain.params.PostSubmitParam
import kr.hs.dgsw.mentomenv2.domain.usecase.file.PostFileUseCase
import kr.hs.dgsw.mentomenv2.domain.usecase.my.GetMyInfoUseCase
import kr.hs.dgsw.mentomenv2.domain.usecase.post.EditPostUseCase
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
    private val getPostByIdUseCase: GetPostByIdUseCase,
    private val editPostUseCase: EditPostUseCase,
    private val getMyInfoUseCase: GetMyInfoUseCase,
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

    fun loadImage() {
        Log.d("loadImage: ", "imgFile : ${imgFile.value}")
        if (imgFile.value.isNullOrEmpty()) {
            viewEvent(LOAD_IMAGE)
            return
        }
        applyEvent("이미지 업로드 중입니다.")
        postFileUseCase(imgFile.value!!).safeApiCall(
            _isLoading,
            successAction = {
                Log.d("PostViewModel", "it: $it")
                val tempList =
                    imgUrl.value.orEmpty().toMutableList() // 현재 리스트 가져오기 (빈 리스트인 경우 빈 리스트로 초기화)
                tempList.addAll(it.orEmpty().toMutableList()) // 새로운 이미지 URL 리스트를 기존 리스트에 추가
                imgUrl.value = tempList // 변경된 리스트를 다시 MutableLiveData에 설정
                Log.d("PostViewModel", "imgUrl in post image: ${imgUrl.value}")
                viewEvent(LOAD_IMAGE)
            },
            errorAction = {
                Log.e("loadImage: fail", "result : $it")
                imgUrl.value = emptyList()
                applyEvent("이미지 업로드에 실패했습니다.")
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
        Log.d("PostViewModel", "imgUrl: ${imgUrl.value}")
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
                applyEvent("게시글 등록에 실패했습니다.")
                Log.e("PostViewModel", it.toString())
            },
        )
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
                applyEvent("게시글 정보를 불러오는데 실패했습니다.")
                Log.e("PostViewModel", it.toString())
            },
        )
    }

    fun editPost(postId: Int) {
        editPostUseCase(
            PostEditParam(
                content.value!!,
                imgUrl.value!!,
                tagState.value!!,
                postId,
            ),
        ).safeApiCall(_isLoading, {
            applyEvent("게시글 수정에 성공했습니다.")
        }, {
            applyEvent("게시글 수정에 실패했습니다.")
        })
    }

    fun getMyInfo() {
        getMyInfoUseCase
            .invoke()
            .safeApiCall(_isLoading, {})
    }

    companion object {
        const val ON_CLICK_IMAGE = 0
        const val ON_CLICK_SUBMIT = 1
        const val LOAD_IMAGE = 2
    }
}
