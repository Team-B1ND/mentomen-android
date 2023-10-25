package kr.hs.dgsw.mentomenv2.di.manager

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kr.hs.dgsw.mentomenv2.domain.datastore.PreferencesKeys
import javax.inject.Inject

class DataStoreManager @Inject constructor(
    private val dataStore: DataStore<Preferences>
){
    fun getDataStoreByKey(key: Preferences.Key<String>): Flow<String?> {
        return dataStore.data.map { prefs ->
            prefs[key]
        }
    }

    suspend fun saveDataStore(key: Preferences.Key<String>,value: String){
        dataStore.edit { prefs ->
            prefs[key] = value
        }
    }

    suspend fun deleteDataStoreByKey(key: Preferences.Key<String>){
        dataStore.edit { prefs ->
            prefs.remove(key)
        }
    }
}