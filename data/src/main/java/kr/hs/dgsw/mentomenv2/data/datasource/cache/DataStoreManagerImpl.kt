package kr.hs.dgsw.mentomenv2.data.datasource.cache

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.map
import kr.hs.dgsw.mentomenv2.data.remote.TokenDataSource
import javax.inject.Inject

class DataStoreManagerImpl @Inject constructor(
    private val dataStore: DataStore<Preferences>
) : TokenDataSource {

    override suspend fun getAccessToken(): String {
        return dataStore.data.map { prefs ->
            prefs[stringPreferencesKey("access_token")]
        }.toString()
    }

    override suspend fun getRefreshToken(): String {
        return dataStore.data.map { prefs ->
            prefs[stringPreferencesKey("refresh_token")]
        }.toString()
    }

    override suspend fun setAccessToken(accessToken: String) {
        dataStore.edit { prefs ->
            prefs[stringPreferencesKey("access_token")] = accessToken
        }
    }

    override suspend fun setRefreshToken(refreshToken: String) {
        dataStore.edit { prefs ->
            prefs[stringPreferencesKey("refresh_token")] = refreshToken
        }
    }
}
