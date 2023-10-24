package kr.hs.dgsw.mentomenv2.feature.main

import androidx.lifecycle.MutableLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kr.hs.dgsw.mentomenv2.base.BaseViewModel
import kr.hs.dgsw.mentomenv2.domain.model.Post
import kr.hs.dgsw.mentomenv2.state.PostState
import javax.inject.Inject

@HiltViewModel
class HomeFragmentViewModel @Inject constructor() : BaseViewModel() {
    val tagState = MutableLiveData<PostState>(PostState.ALL)
    val itemList = MutableStateFlow<List<Post>>(emptyList())
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