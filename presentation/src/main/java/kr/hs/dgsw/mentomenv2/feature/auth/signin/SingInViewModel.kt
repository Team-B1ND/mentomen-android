package kr.hs.dgsw.mentomenv2.feature.auth.signin

import android.util.Log
import androidx.lifecycle.MutableLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kr.hs.dgsw.mentomenv2.base.BaseViewModel
import kr.hs.dgsw.mentomenv2.domain.model.Token
import kr.hs.dgsw.mentomenv2.domain.params.DAuthParam
import kr.hs.dgsw.mentomenv2.domain.repository.TokenRepository
import kr.hs.dgsw.smartschool.dodamdodam.widget.Event
import javax.inject.Inject

@HiltViewModel
class SingInViewModel @Inject constructor(
    private val tokenRepository: TokenRepository,
) : BaseViewModel() {
    private val _event = MutableSharedFlow<Event<String?>>()
    val event = _event.asSharedFlow()
    val code = MutableStateFlow<String>("")
    val tokenState = MutableStateFlow<Token>(Token("", ""))
    private val isLoading: MutableLiveData<Boolean> = MutableLiveData(false)
    val pw = MutableStateFlow<String>("")
    val id = MutableStateFlow<String>("")

    fun onClickLogin() {

    }

    init {
        tokenRepository.getToken().safeApiCall(
            successAction = {
                tokenState.value = it?: Token("", "")
            },
            errorAction = {
                Log.d("error", "error: $it")
            }
        )
    }
}
