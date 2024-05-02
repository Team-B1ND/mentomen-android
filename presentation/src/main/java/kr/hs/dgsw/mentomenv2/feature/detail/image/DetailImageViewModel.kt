package kr.hs.dgsw.mentomenv2.feature.detail.image

import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.flow.MutableStateFlow
import kr.hs.dgsw.mentomenv2.base.BaseViewModel

class DetailImageViewModel: BaseViewModel() {
    val titleText = MutableStateFlow<String>("1 / 1")
}
