package kr.hs.dgsw.mentomenv2.feature.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import kr.hs.dgsw.mentomenv2.base.BaseViewModel
import kr.hs.dgsw.mentomenv2.domain.model.Post
import kr.hs.dgsw.mentomenv2.domain.repository.PostRepository
import kr.hs.dgsw.mentomenv2.state.PostState
import javax.inject.Inject

@HiltViewModel
class HomeFragmentViewModel @Inject constructor(
    private val postRepository: PostRepository
) : BaseViewModel() {
    val tagState = MutableStateFlow<PostState>(PostState.ALL)
    val itemList = MutableLiveData<List<Post>>()
    private val _errorFlow = MutableSharedFlow<String?>()
    val errorFlow = _errorFlow.asSharedFlow()

    init {
        getAllPost()
    }

    fun getAllPost() = viewModelScope.launch(Dispatchers.IO) {
        kotlin.runCatching {
            tagState.emit(PostState.ALL)
            postRepository.getAllPost()
        }.onSuccess {
            itemList.postValue(it)
        }.onFailure {
            _errorFlow.emit(it.message)
        }
    }

    fun onClickDesignBtn() = viewModelScope.launch(Dispatchers.IO) {
        kotlin.runCatching {
            tagState.emit(PostState.DESIGN)
            postRepository.getPostByTag("DESIGN")
        }.onSuccess {
            itemList.postValue(it)
        }.onFailure {
            _errorFlow.emit(it.message)
        }
    }

    fun onClickWebBtn() = viewModelScope.launch(Dispatchers.IO) {
        kotlin.runCatching {
            tagState.emit(PostState.WEB)
            postRepository.getPostByTag("WEB")
        }.onSuccess {
            itemList.postValue(it)
        }.onFailure {
            _errorFlow.emit(it.message)
        }
    }

    fun onClickAndroidBtn() = viewModelScope.launch(Dispatchers.IO) {
        kotlin.runCatching {
            tagState.emit(PostState.ANDROID)
            postRepository.getPostByTag("ANDROID")
        }.onSuccess {
            itemList.postValue(it)
        }.onFailure {
            _errorFlow.emit(it.message)
        }
    }

    fun onClickServerBtn() = viewModelScope.launch(Dispatchers.IO) {
        kotlin.runCatching {
            tagState.emit(PostState.SERVER)
            postRepository.getPostByTag("SERVER")
        }.onSuccess {
            itemList.postValue(it)
        }.onFailure {
            _errorFlow.emit(it.message)
        }
    }

    fun onClickIOSBtn() = viewModelScope.launch(Dispatchers.IO) {
        kotlin.runCatching {
            tagState.emit(PostState.IOS)
            postRepository.getPostByTag("IOS")
        }.onSuccess {
            itemList.postValue(it)
        }.onFailure {
            _errorFlow.emit(it.message)
        }
    }
}
