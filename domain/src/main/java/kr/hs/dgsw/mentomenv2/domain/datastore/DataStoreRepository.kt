package kr.hs.dgsw.mentomenv2.domain.datastore

import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.emptyPreferences
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kr.hs.dgsw.mentomenv2.domain.model.Token
import java.io.IOException


interface UserPreferencesRepository {

    suspend fun setName(
        name: String
    )

    suspend fun getName(): Result<String>
}

/* data layer
*/
/*
class DataStoreRepository(private val preferencesDataStore: DataStore<Preferences>) {

    suspend fun getTokenPreferences(): Flow<Token> = preferencesDataStore.data
        .catch { exception ->
            if (exception is IOException) {
                Log.e("Error getTokenPreferences", exception.message.toString())
                emit(emptyPreferences())
            } else {
                throw exception
            }
        }.map { preferences ->
            mapTokenPreferences(preferences)
        }

    private fun mapTokenPreferences(preferences: Preferences): Token {
        val accessToken = preferences[PreferencesKeys.ACCESS_TOKEN] ?: ""
        val refreshToken = preferences[PreferencesKeys.REFRESH_TOKEN] ?: ""
        return Token(accessToken, refreshToken)
    }
}*/