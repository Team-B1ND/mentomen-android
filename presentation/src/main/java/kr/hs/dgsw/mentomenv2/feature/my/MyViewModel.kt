package kr.hs.dgsw.mentomenv2.feature.my

import android.util.Log
import androidx.lifecycle.MutableLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import kr.hs.dgsw.mentomenv2.R
import kr.hs.dgsw.mentomenv2.base.BaseViewModel
import kr.hs.dgsw.mentomenv2.domain.model.Post
import kr.hs.dgsw.mentomenv2.domain.model.StdInfo
import kr.hs.dgsw.mentomenv2.domain.repository.DataStoreRepository
import kr.hs.dgsw.mentomenv2.domain.repository.MyRepository
import javax.inject.Inject

@HiltViewModel
class MyViewModel
@Inject
constructor(
    private val dataStoreRepository: DataStoreRepository,
    private val myRepository: MyRepository,
) : BaseViewModel() {
    val stdInfo: MutableLiveData<StdInfo> = MutableLiveData(StdInfo(0, 0, 0))
    val userName: MutableLiveData<String> = MutableLiveData("")
    val userProfileUrl: MutableLiveData<String> = MutableLiveData("")
    val post = MutableLiveData<List<Post>>()
    private val _isLoading = MutableLiveData<Boolean>(false)
    val isLoading:MutableLiveData<Boolean> = _isLoading
    fun getMyInfo() {
        myRepository.getMyInfo().safeApiCall(
            _isLoading,
            {
                userName.value = it?.name ?: ""
                stdInfo.value = it?.stdInfo ?: StdInfo(0, 0, 0)
                userProfileUrl.value = it?.profileImage ?: R.drawable.ic_default_user.toString()
            }
        )
    }

    fun getMyPost() {
        myRepository.getMyPost().safeApiCall(
            _isLoading,
            {
                post.value = it
            },
        )
    }

    fun clearDataStore() {
        dataStoreRepository.clearData().safeApiCall(
            _isLoading,
            {
                Log.d("logout: ", "dataStore 비우기 성공")
            },
        )
    }
}
