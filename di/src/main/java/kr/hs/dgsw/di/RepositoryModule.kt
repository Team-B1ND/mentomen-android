package kr.hs.dgsw.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kr.hs.dgsw.mentomenv2.data.datasource.cache.DataStoreDataSourceImpl
import kr.hs.dgsw.mentomenv2.data.datasource.remote.AuthDataSourceImpl
import kr.hs.dgsw.mentomenv2.data.datasource.remote.PostDataSourceImpl
import kr.hs.dgsw.mentomenv2.data.remote.AuthDataSource
import kr.hs.dgsw.mentomenv2.data.remote.PostDataSource
import kr.hs.dgsw.mentomenv2.data.remote.DataStoreDataSource
import kr.hs.dgsw.mentomenv2.data.repository.AuthRepositoryImpl
import kr.hs.dgsw.mentomenv2.data.repository.PostRepositoryImplImpl
import kr.hs.dgsw.mentomenv2.data.repository.DataStoreRepositoryImpl
import kr.hs.dgsw.mentomenv2.domain.repository.AuthRepository
import kr.hs.dgsw.mentomenv2.domain.repository.PostRepository
import kr.hs.dgsw.mentomenv2.domain.repository.DataStoreRepository
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
        tokenRepositoryImpl: DataStoreRepositoryImpl
    ): DataStoreRepository

    @Binds
    @Singleton
    fun bindsDataStore(
        tokenDataSourceImpl: DataStoreDataSourceImpl
    ): DataStoreDataSource

    @Binds
    @Singleton
    fun bindsPostRepository(
        postRepositoryImpl: PostRepositoryImplImpl
    ): PostRepository

    @Binds
    @Singleton
    fun bindsPostDataSource(
        postDataSourceImpl: PostDataSourceImpl
    ): PostDataSource
}
