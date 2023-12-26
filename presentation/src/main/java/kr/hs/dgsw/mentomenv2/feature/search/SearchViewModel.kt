package kr.hs.dgsw.mentomenv2.feature.search

import androidx.lifecycle.MutableLiveData
import kr.hs.dgsw.mentomenv2.base.BaseViewModel

class SearchViewModel : BaseViewModel() {
    val keyWord = MutableLiveData<String>("")
}
