package kr.hs.dgsw.mentomenv2.feature.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kr.hs.dgsw.mentomenv2.base.BaseViewModel
import kr.hs.dgsw.mentomenv2.domain.model.Post
import kr.hs.dgsw.mentomenv2.domain.model.StdInfo
import kr.hs.dgsw.mentomenv2.domain.repository.PostRepository
import kr.hs.dgsw.mentomenv2.state.PostState
import javax.inject.Inject

@HiltViewModel
class HomeFragmentViewModel @Inject constructor(
    private val postRepository: PostRepository
) : BaseViewModel() {
    val tagState = MutableStateFlow<PostState>(PostState.ALL)
    val itemList = MutableLiveData<List<Post>>()

    init {
        getAllPost()
    }

    fun getAllPost() {
        kotlin.runCatching {
            viewModelScope.launch(Dispatchers.IO) {
                itemList.postValue(postRepository.getAllPost())
            }
        }
    }

    fun onClickDesignBtn() {
        kotlin.runCatching {
            viewModelScope.launch(Dispatchers.IO) {
                tagState.emit(PostState.DESIGN)
                itemList.postValue(postRepository.getPostByTag("DESIGN"))
            }
        }
    }

    fun onClickWebBtn() {
        kotlin.runCatching {
            viewModelScope.launch(Dispatchers.IO) {
                tagState.emit(PostState.WEB)
                itemList.postValue(postRepository.getPostByTag("WEB"))
            }
        }
    }

    fun onClickAndroidBtn() {
        kotlin.runCatching {
            viewModelScope.launch(Dispatchers.IO) {
                tagState.emit(PostState.ANDROID)
                itemList.postValue(postRepository.getPostByTag("ANDROID"))
            }
        }
    }

    fun onClickServerBtn() {
        kotlin.runCatching {
            viewModelScope.launch(Dispatchers.IO) {
                tagState.emit(PostState.SERVER)
                itemList.postValue(postRepository.getPostByTag("SERVER"))
            }
        }
    }

    fun onClickIOSBtn() {
        kotlin.runCatching {
            viewModelScope.launch(Dispatchers.IO) {
                tagState.emit(PostState.IOS)
                itemList.postValue(postRepository.getPostByTag("IOS"))
            }
        }
    }
}
