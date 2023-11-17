package kr.hs.dgsw.mentomenv2.data.datasource.cache

import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kr.hs.dgsw.mentomenv2.data.remote.DataStoreDataSource
import kr.hs.dgsw.mentomenv2.domain.model.Token
import javax.inject.Inject

class DataStoreDataSourceImpl @Inject constructor(
    private val dataStore: DataStore<Preferences>
) : DataStoreDataSource {
    override suspend fun saveData(key: String, value: String) {
        val preferencesKey = stringPreferencesKey(key)
        dataStore.edit { preferences ->
            preferences[preferencesKey] = value
        }
    }

    override fun getToken(): Flow<Token> = flow {
        val accessToken = dataStore.data.map { preferences ->
            preferences[stringPreferencesKey("access_token")] ?: ""
        }
        val refreshToken = dataStore.data.map { preferences ->
            preferences[stringPreferencesKey("refresh_token")] ?: ""
        }
        Token(accessToken.first(), refreshToken.first())
    }

    override fun getData(key: String, defaultValue: String): Flow<String> = flow {
        val preferencesKey = stringPreferencesKey(key)
        dataStore.data.map { preferences ->
            preferences[preferencesKey] ?: defaultValue
        }
    }

    override suspend fun removeData(key: String) {
        val preferencesKey = stringPreferencesKey(key)
        dataStore.edit { preferences ->
            preferences.remove(preferencesKey)
        }
    }

    override suspend fun clearData() {
        dataStore.edit { preferences ->
            preferences.clear()
        }
    }
}
