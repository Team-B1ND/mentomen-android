package kr.hs.dgsw.mentomenv2.data.datasource.cache

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kr.hs.dgsw.mentomenv2.data.remote.TokenDataSource
import kr.hs.dgsw.mentomenv2.domain.model.Token
import javax.inject.Inject

class TokenDataSourceImpl @Inject constructor(
    private val dataStore: DataStore<Preferences>
) : TokenDataSource {

    override fun getToken(): Flow<Token> {
        val accessToken = dataStore.data.map { prefs ->
            prefs[stringPreferencesKey("access_token")]
        }

        val refreshToken = dataStore.data.map { prefs ->
            prefs[stringPreferencesKey("refresh_token")]
        }
        return flow {
            emit(Token(accessToken.first() ?: "", refreshToken.first() ?: ""))
        }
    }

    override fun setToken(token: Token) {
        suspend {
            dataStore.edit { prefs ->
                prefs[stringPreferencesKey("access_token")] = token.accessToken
                prefs[stringPreferencesKey("refresh_token")] = token.refreshToken
            }
        }
    }

    override fun deleteToken() {
        suspend {
            dataStore.edit { prefs ->
                prefs.remove(stringPreferencesKey("access_token"))
                prefs.remove(stringPreferencesKey("refresh_token"))
            }
        }
    }
}
