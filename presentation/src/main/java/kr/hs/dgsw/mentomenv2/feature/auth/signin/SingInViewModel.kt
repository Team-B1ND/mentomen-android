package kr.hs.dgsw.mentomenv2.feature.auth.signin

import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import kr.hs.dgsw.mentomenv2.base.BaseViewModel
import kr.hs.dgsw.mentomenv2.di.manager.DataStoreManager
import kr.hs.dgsw.mentomenv2.domain.datastore.PreferencesKeys
import kr.hs.dgsw.mentomenv2.domain.model.Token
import javax.inject.Inject

@HiltViewModel
class SingInViewModel @Inject constructor(
    private val dataStore: DataStore<Preferences>
) : BaseViewModel() {

    fun setToken(token: Token) {
        val dataStoreManager = DataStoreManager(dataStore)
        viewModelScope.launch {
            dataStore.edit { preferences ->
//                preferences[PreferencesKeys.ACCESS_TOKEN] = token.accessToken
//                preferences[PreferencesKeys.REFRESH_TOKEN] = token.refreshToken
                dataStoreManager.saveDataStore(PreferencesKeys.ACCESS_TOKEN, token.accessToken)
                dataStoreManager.saveDataStore(PreferencesKeys.REFRESH_TOKEN, token.refreshToken)
                Log.d("setToken:2222222222222", dataStoreManager.getDataStoreByKey(PreferencesKeys.ACCESS_TOKEN).toString())
                Log.d("setToken:3333333333333", dataStoreManager.getDataStoreByKey(PreferencesKeys.REFRESH_TOKEN).toString())
            }
        }
    }
}
