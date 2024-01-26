package kr.hs.dgsw.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kr.hs.dgsw.mentomenv2.data.datasource.AuthDataSource
import kr.hs.dgsw.mentomenv2.data.datasource.CommentDataSource
import kr.hs.dgsw.mentomenv2.data.datasource.DataStoreDataSource
import kr.hs.dgsw.mentomenv2.data.datasource.FileDataSource
import kr.hs.dgsw.mentomenv2.data.datasource.MyDataSource
import kr.hs.dgsw.mentomenv2.data.datasource.PostDataSource
import kr.hs.dgsw.mentomenv2.data.datasourceimpl.cache.DataStoreDataSourceImpl
import kr.hs.dgsw.mentomenv2.data.datasourceimpl.remote.AuthDataSourceImpl
import kr.hs.dgsw.mentomenv2.data.datasourceimpl.remote.CommentDataSourceImpl
import kr.hs.dgsw.mentomenv2.data.datasourceimpl.remote.FileDataSourceImpl
import kr.hs.dgsw.mentomenv2.data.datasourceimpl.remote.MyDataSourceImpl
import kr.hs.dgsw.mentomenv2.data.datasourceimpl.remote.PostDataSourceImpl
import kr.hs.dgsw.mentomenv2.data.repository.AuthRepositoryImpl
import kr.hs.dgsw.mentomenv2.data.repository.CommentRepositoryImpl
import kr.hs.dgsw.mentomenv2.data.repository.DataStoreRepositoryImpl
import kr.hs.dgsw.mentomenv2.data.repository.FileRepositoryImpl
import kr.hs.dgsw.mentomenv2.data.repository.MyRepositoryImpl
import kr.hs.dgsw.mentomenv2.data.repository.PostRepositoryImplImpl
import kr.hs.dgsw.mentomenv2.domain.repository.AuthRepository
import kr.hs.dgsw.mentomenv2.domain.repository.CommentRepository
import kr.hs.dgsw.mentomenv2.domain.repository.DataStoreRepository
import kr.hs.dgsw.mentomenv2.domain.repository.FileRepository
import kr.hs.dgsw.mentomenv2.domain.repository.MyRepository
import kr.hs.dgsw.mentomenv2.domain.repository.PostRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {
    @Binds
    @Singleton
    fun bindsAuthRepository(authRepositoryImpl: AuthRepositoryImpl): AuthRepository

    @Binds
    @Singleton
    fun bindsAuthDataSource(authDataSourceImpl: AuthDataSourceImpl): AuthDataSource

    @Binds
    @Singleton
    fun bindsDataStoreRepository(dataStoreRepositoryImpl: DataStoreRepositoryImpl): DataStoreRepository

    @Binds
    @Singleton
    fun bindsDataStore(tokenDataSourceImpl: DataStoreDataSourceImpl): DataStoreDataSource

    @Binds
    @Singleton
    fun bindsPostRepository(postRepositoryImpl: PostRepositoryImplImpl): PostRepository

    @Binds
    @Singleton
    fun bindsPostDataSource(postDataSourceImpl: PostDataSourceImpl): PostDataSource

    @Binds
    @Singleton
    fun bindsFileRepository(fileRepositoryImpl: FileRepositoryImpl): FileRepository

    @Binds
    @Singleton
    fun bindsFileDataSource(fileDataSourceImpl: FileDataSourceImpl): FileDataSource

    @Binds
    @Singleton
    fun bindsCommentRepository(commentRepositoryImpl: CommentRepositoryImpl): CommentRepository

    @Binds
    @Singleton
    fun bindsCommentDataSource(commentDataSourceImpl: CommentDataSourceImpl): CommentDataSource

    @Binds
    @Singleton
    fun bindsMyRepository(myRepositoryImpl: MyRepositoryImpl): MyRepository

    @Binds
    @Singleton
    fun bindsMyDataSource(myDataSourceImpl: MyDataSourceImpl): MyDataSource
}
