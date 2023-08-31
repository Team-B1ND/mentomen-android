package kr.hs.dgsw.mentomenv2.base

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

open class BaseViewModel : ViewModel() {
    protected val _loading = MutableStateFlow(false)
    val loading: StateFlow<Boolean> = _loading.asStateFlow()

    protected val _error = MutableStateFlow("")
    val error: StateFlow<String> = _error.asStateFlow()
}
