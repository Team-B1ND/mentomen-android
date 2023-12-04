package kr.hs.dgsw.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kr.hs.dgsw.mentomenv2.domain.repository.PostRepository
import kr.hs.dgsw.mentomenv2.domain.usecase.post.GetAllPostUseCase
import kr.hs.dgsw.mentomenv2.domain.usecase.post.GetPostsByTagUseCase
import kr.hs.dgsw.mentomenv2.domain.usecase.post.PostSubmitUseCase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class UseCasesModule {
    @Provides
    @Singleton
    fun providePostCases(repository: PostRepository): PostUseCases =
        PostUseCases(
            getAllPostUseCase = GetAllPostUseCase(repository),
            getPostsByTagUseCases = GetPostsByTagUseCase(repository),
            submitUseCase = PostSubmitUseCase(repository)
        )
}
