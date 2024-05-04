package com.b1nd.mentomen.di

import com.b1nd.mentomen.data.repository.AuthRepositoryImpl
import com.b1nd.mentomen.data.repository.CommentRepositoryImpl
import com.b1nd.mentomen.data.repository.DAuthRepositoryImpl
import com.b1nd.mentomen.data.repository.DataStoreRepositoryImpl
import com.b1nd.mentomen.data.repository.FileRepositoryImpl
import com.b1nd.mentomen.data.repository.MyRepositoryImpl
import com.b1nd.mentomen.data.repository.NoticeRepositoryImpl
import com.b1nd.mentomen.data.repository.PostRepositoryImpl
import com.b1nd.mentomen.domain.repository.AuthRepository
import com.b1nd.mentomen.domain.repository.CommentRepository
import com.b1nd.mentomen.domain.repository.DAuthRepository
import com.b1nd.mentomen.domain.repository.DataStoreRepository
import com.b1nd.mentomen.domain.repository.FileRepository
import com.b1nd.mentomen.domain.repository.MyRepository
import com.b1nd.mentomen.domain.repository.NoticeRepository
import com.b1nd.mentomen.domain.repository.PostRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {
    @Binds
    @Singleton
    fun bindsAuthRepository(authRepositoryImpl: AuthRepositoryImpl): AuthRepository

    @Binds
    @Singleton
    fun bindsDataStoreRepository(dataStoreRepositoryImpl: DataStoreRepositoryImpl): DataStoreRepository

    @Binds
    @Singleton
    fun bindsPostRepository(postRepositoryImpl: PostRepositoryImpl): PostRepository

    @Binds
    @Singleton
    fun bindsFileRepository(fileRepositoryImpl: FileRepositoryImpl): FileRepository

    @Binds
    @Singleton
    fun bindsCommentRepository(commentRepositoryImpl: CommentRepositoryImpl): CommentRepository

    @Binds
    @Singleton
    fun bindsMyRepository(myRepositoryImpl: MyRepositoryImpl): MyRepository

    @Binds
    @Singleton
    fun bindsDAuthRepository(dAuthRepositoryImpl: DAuthRepositoryImpl): DAuthRepository

    @Binds
    @Singleton
    fun bindsNoticeRepository(noticeRepositoryImpl: NoticeRepositoryImpl): NoticeRepository
}
