package kr.hs.dgsw.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kr.hs.dgsw.mentomenv2.data.datasource.AuthDataSource
import kr.hs.dgsw.mentomenv2.data.datasource.CommentDataSource
import kr.hs.dgsw.mentomenv2.data.datasource.DAuthDataSource
import kr.hs.dgsw.mentomenv2.data.datasource.DataStoreDataSource
import kr.hs.dgsw.mentomenv2.data.datasource.FileDataSource
import kr.hs.dgsw.mentomenv2.data.datasource.MyDataSource
import kr.hs.dgsw.mentomenv2.data.datasource.MyProfileDataSource
import kr.hs.dgsw.mentomenv2.data.datasource.NoticeDataSource
import kr.hs.dgsw.mentomenv2.data.datasource.PostDataSource
import kr.hs.dgsw.mentomenv2.data.datasourceimpl.cache.DataStoreDataSourceImpl
import kr.hs.dgsw.mentomenv2.data.datasourceimpl.remote.AuthDataSourceImpl
import kr.hs.dgsw.mentomenv2.data.datasourceimpl.remote.CommentDataSourceImpl
import kr.hs.dgsw.mentomenv2.data.datasourceimpl.remote.DAuthDataSourceImpl
import kr.hs.dgsw.mentomenv2.data.datasourceimpl.remote.FileDataSourceImpl
import kr.hs.dgsw.mentomenv2.data.datasourceimpl.remote.MyDataSourceImpl
import kr.hs.dgsw.mentomenv2.data.datasourceimpl.remote.MyProfileDataSourceImpl
import kr.hs.dgsw.mentomenv2.data.datasourceimpl.remote.NoticeDataSourceImpl
import kr.hs.dgsw.mentomenv2.data.datasourceimpl.remote.PostDataSourceImpl
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

    @Binds
    @Singleton
    fun bindsMyProfileDataSource(myProfileDataSourceImpl: MyProfileDataSourceImpl): MyProfileDataSource
}
