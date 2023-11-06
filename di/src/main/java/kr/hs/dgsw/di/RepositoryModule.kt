package kr.hs.dgsw.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kr.hs.dgsw.mentomenv2.data.datasource.cache.TokenDataSourceImpl
import kr.hs.dgsw.mentomenv2.data.datasource.remote.AuthDataSourceImpl
import kr.hs.dgsw.mentomenv2.data.datasource.remote.PostDataSourceImpl
import kr.hs.dgsw.mentomenv2.data.remote.AuthDataSource
import kr.hs.dgsw.mentomenv2.data.remote.PostDataSource
import kr.hs.dgsw.mentomenv2.data.remote.TokenDataSource
import kr.hs.dgsw.mentomenv2.data.repository.AuthRepositoryImpl
import kr.hs.dgsw.mentomenv2.data.repository.PostRepositoryImpl
import kr.hs.dgsw.mentomenv2.data.repository.TokenRepositoryImpl
import kr.hs.dgsw.mentomenv2.domain.repository.AuthRepository
import kr.hs.dgsw.mentomenv2.domain.repository.PostRepository
import kr.hs.dgsw.mentomenv2.domain.repository.TokenRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {
    @Binds
    @Singleton
    fun bindsAuthRepository(
        authRepositoryImpl: AuthRepositoryImpl
    ): AuthRepository

    @Binds
    @Singleton
    fun bindsAuthDataSource(
        authDataSourceImpl: AuthDataSourceImpl
    ): AuthDataSource

    @Binds
    @Singleton
    fun bindsTokenRepository(
        tokenRepositoryImpl: TokenRepositoryImpl
    ): TokenRepository

    @Binds
    @Singleton
    fun bindsDataStore(
        tokenDataSourceImpl: TokenDataSourceImpl
    ): TokenDataSource

    @Binds
    @Singleton
    fun bindsPostRepository(
        postRepositoryImpl: PostRepositoryImpl
    ): PostRepository

    @Binds
    @Singleton
    fun bindsPostDataSource(
        postDataSourceImpl: PostDataSourceImpl
    ): PostDataSource
}
