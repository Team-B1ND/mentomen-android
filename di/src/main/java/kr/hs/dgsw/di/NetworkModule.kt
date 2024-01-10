package kr.hs.dgsw.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.core.handlers.ReplaceFileCorruptionHandler
import androidx.datastore.preferences.SharedPreferencesMigration
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.preferencesDataStoreFile
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kr.hs.dgsw.mentomenv2.data.interceptor.Intercept
import kr.hs.dgsw.mentomenv2.data.repository.DataStoreRepositoryImpl
import kr.hs.dgsw.mentomenv2.data.service.AuthService
import kr.hs.dgsw.mentomenv2.data.service.CommentService
import kr.hs.dgsw.mentomenv2.data.service.FileService
import kr.hs.dgsw.mentomenv2.data.service.MyService
import kr.hs.dgsw.mentomenv2.data.service.PostService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Singleton
    @Provides
    fun provideInterceptor(tokenRepositoryImpl: DataStoreRepositoryImpl): Intercept {
        return Intercept(tokenRepositoryImpl)
    }

    @Singleton
    @Provides
    fun provideOkHttpClient(
        loggerInterceptor: HttpLoggingInterceptor,
        intercept: Intercept,
    ): OkHttpClient {
        val okHttpClientBuilder = OkHttpClient().newBuilder()
        okHttpClientBuilder.connectTimeout(60, TimeUnit.SECONDS)
        okHttpClientBuilder.readTimeout(60, TimeUnit.SECONDS)
        okHttpClientBuilder.writeTimeout(60, TimeUnit.SECONDS)
        okHttpClientBuilder.addInterceptor(loggerInterceptor)
        okHttpClientBuilder.addInterceptor(intercept)
        return okHttpClientBuilder.build()
    }

    @Singleton
    @Provides
    fun provideRetrofit(
        okHttpClient: OkHttpClient,
        gsonConverterFactory: GsonConverterFactory,
    ): Retrofit {
        //http://localhost:8080/
        return Retrofit.Builder()
            .baseUrl("https://mentomen.team-alt.com/")
            .client(okHttpClient)
            .addConverterFactory(gsonConverterFactory)
            .build()
    }

    @Singleton
    @Provides
    fun provideGsonConverter(): GsonConverterFactory = GsonConverterFactory.create(GsonBuilder().create())

    @Provides
    @Singleton
    fun provideLoggingInterceptor(): HttpLoggingInterceptor = HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY }

    private const val USER_PREFERENCES = "user_preferences"

    @Singleton
    @Provides
    fun providePreferencesDataStore(
        @ApplicationContext appContext: Context,
    ): DataStore<Preferences> {
        return PreferenceDataStoreFactory.create(
            corruptionHandler =
                ReplaceFileCorruptionHandler(
                    produceNewData = { emptyPreferences() },
                ),
            migrations = listOf(SharedPreferencesMigration(appContext, USER_PREFERENCES)),
            scope = CoroutineScope(Dispatchers.IO + SupervisorJob()),
            produceFile = { appContext.preferencesDataStoreFile(USER_PREFERENCES) },
        )
    }

    @Singleton
    @Provides
    fun providesPostRepository(retrofit: Retrofit): PostService = retrofit.create(PostService::class.java)

    @Singleton
    @Provides
    fun providesUserRepository(retrofit: Retrofit): AuthService = retrofit.create(AuthService::class.java)

    @Singleton
    @Provides
    fun provideFileRepository(retrofit: Retrofit): FileService = retrofit.create(FileService::class.java)

    @Singleton
    @Provides
    fun provideCommentRepository(retrofit: Retrofit): CommentService = retrofit.create(CommentService::class.java)

    @Singleton
    @Provides
    fun provideMyRepository(retrofit: Retrofit): MyService = retrofit.create(MyService::class.java)
}
