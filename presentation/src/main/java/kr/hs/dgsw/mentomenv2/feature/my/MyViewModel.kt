package kr.hs.dgsw.mentomenv2.feature.my

import android.util.Log
import androidx.lifecycle.MutableLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import kr.hs.dgsw.mentomenv2.base.BaseViewModel
import kr.hs.dgsw.mentomenv2.domain.model.Post
import kr.hs.dgsw.mentomenv2.domain.model.StdInfo
import kr.hs.dgsw.mentomenv2.domain.repository.DataStoreRepository
import kr.hs.dgsw.mentomenv2.domain.repository.MyRepository
import kr.hs.dgsw.mentomenv2.domain.usecase.my.GetMyInfoUseCase
import javax.inject.Inject

@HiltViewModel
class MyViewModel
@Inject
constructor(
    private val dataStoreRepository: DataStoreRepository,
    private val myRepository: MyRepository
) : BaseViewModel() {
    val stdInfo: MutableLiveData<StdInfo> = MutableLiveData(StdInfo(0, 0, 0))
    val userName: MutableLiveData<String> = MutableLiveData("")
    val post = MutableLiveData<List<Post>>()

    private fun getMyInfo() {
        myRepository.getMyInfo().safeApiCall(
            null,
            {
                userName.value = it?.name ?: ""
                stdInfo.value = it?.stdInfo ?: StdInfo(0, 0, 0)
            },
            {
                Log.d("getMyInfo: ", error.value.toString())
            }
        )
    }

    private fun getMyPost() {
        myRepository.getMyPost().safeApiCall(
            null,
            {
                post.value = it
            },
            {
                Log.d("getMyPost: ", error.value.toString())
            }
        )
    }

    fun logout() {
        dataStoreRepository.clearData().safeApiCall(
            null,
            successAction = {
                Log.d("logout: ", "dataStore 비우기 성공")
            },
            errorAction = {
                Log.d("logout: ", error.value.toString())
            },
        )
    }
}
