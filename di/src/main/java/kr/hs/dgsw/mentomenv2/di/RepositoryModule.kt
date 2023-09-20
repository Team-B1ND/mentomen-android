package kr.hs.dgsw.mentomenv2.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kr.hs.dgsw.mentomenv2.data.repository.DAuthSignInRepositoryImpl
import kr.hs.dgsw.mentomenv2.domain.repository.AuthRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {
    @Binds
    @Singleton
    fun bindsAuthRepository(
        authRepositoryImpl: DAuthSignInRepositoryImpl
    ): AuthRepository
}