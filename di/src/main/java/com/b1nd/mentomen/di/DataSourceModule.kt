package com.b1nd.mentomen.di

import com.b1nd.mentomen.data.datasource.AuthDataSource
import com.b1nd.mentomen.data.datasource.CommentDataSource
import com.b1nd.mentomen.data.datasource.DAuthDataSource
import com.b1nd.mentomen.data.datasource.DataStoreDataSource
import com.b1nd.mentomen.data.datasource.FileDataSource
import com.b1nd.mentomen.data.datasource.MyDataSource
import com.b1nd.mentomen.data.datasource.NoticeDataSource
import com.b1nd.mentomen.data.datasource.PostDataSource
import com.b1nd.mentomen.data.datasourceimpl.cache.DataStoreDataSourceImpl
import com.b1nd.mentomen.data.datasourceimpl.remote.AuthDataSourceImpl
import com.b1nd.mentomen.data.datasourceimpl.remote.CommentDataSourceImpl
import com.b1nd.mentomen.data.datasourceimpl.remote.DAuthDataSourceImpl
import com.b1nd.mentomen.data.datasourceimpl.remote.FileDataSourceImpl
import com.b1nd.mentomen.data.datasourceimpl.remote.MyDataSourceImpl
import com.b1nd.mentomen.data.datasourceimpl.remote.NoticeDataSourceImpl
import com.b1nd.mentomen.data.datasourceimpl.remote.PostDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface DataSourceModule {
    @Binds
    @Singleton
    fun bindsAuthDataSource(authDataSourceImpl: AuthDataSourceImpl): AuthDataSource

    @Binds
    @Singleton
    fun bindsDataStore(tokenDataSourceImpl: DataStoreDataSourceImpl): DataStoreDataSource

    @Binds
    @Singleton
    fun bindsPostDataSource(postDataSourceImpl: PostDataSourceImpl): PostDataSource

    @Binds
    @Singleton
    fun bindsFileDataSource(fileDataSourceImpl: FileDataSourceImpl): FileDataSource

    @Binds
    @Singleton
    fun bindsCommentDataSource(commentDataSourceImpl: CommentDataSourceImpl): CommentDataSource

    @Binds
    @Singleton
    fun bindsMyDataSource(myDataSourceImpl: MyDataSourceImpl): MyDataSource

    @Binds
    @Singleton
    fun bindsDAuthDataSource(dAuthDataSourceImpl: DAuthDataSourceImpl): DAuthDataSource

    @Binds
    @Singleton
    fun bindsNoticeDataSource(noticeDataSourceImpl: NoticeDataSourceImpl): NoticeDataSource
}
