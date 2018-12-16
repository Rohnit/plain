package io.plainapp.core.dagger.designernews

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import dagger.Module
import dagger.Provides
import io.plainapp.core.BuildConfig
import io.plainapp.core.dagger.CoroutinesDispatcherProviderModule
import io.plainapp.core.dagger.SharedPreferencesModule
import io.plainapp.core.data.CoroutinesDispatcherProvider
import io.plainapp.core.data.api.DenvelopingConverter
import io.plainapp.core.designernews.data.api.ClientAuthInterceptor
import io.plainapp.core.designernews.data.api.DesignerNewsService
import io.plainapp.core.designernews.data.login.AuthTokenLocalDataSource
import io.plainapp.core.designernews.data.login.LoginLocalDataSource
import io.plainapp.core.designernews.data.login.LoginRemoteDataSource
import io.plainapp.core.designernews.data.login.LoginRepository
import io.plainapp.core.designernews.data.stories.StoriesRemoteDataSource
import io.plainapp.core.designernews.data.stories.StoriesRepository
import io.plainapp.core.designernews.domain.LoadStoriesUseCase
import io.plainapp.core.designernews.domain.SearchStoriesUseCase
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Dagger module to provide data functionality for DesignerNews.
 */
@Module(includes = [SharedPreferencesModule::class, CoroutinesDispatcherProviderModule::class])
class DesignerNewsDataModule {

    @Provides
    fun provideLoginRepository(
            localSource: LoginLocalDataSource,
            remoteSource: LoginRemoteDataSource
    ): LoginRepository =
            LoginRepository.getInstance(localSource, remoteSource)

    @Provides
    fun provideLoginLocalDataSource(preferences: SharedPreferences): LoginLocalDataSource =
            LoginLocalDataSource(preferences)

    @Provides
    fun provideLoginRemoteDataSource(
            service: DesignerNewsService,
            tokenHolder: AuthTokenLocalDataSource
    ): LoginRemoteDataSource =
            LoginRemoteDataSource(tokenHolder, service)

    @Provides
    fun provideAuthTokenLocalDataSource(
            sharedPreferences: SharedPreferences
    ): AuthTokenLocalDataSource =
            AuthTokenLocalDataSource.getInstance(sharedPreferences)

    @Provides
    fun provideDesignerNewsService(
            tokenHolder: AuthTokenLocalDataSource,
            loggingInterceptor: HttpLoggingInterceptor,
            gson: Gson
    ): DesignerNewsService {
        val client = OkHttpClient.Builder()
                .addInterceptor(
                        ClientAuthInterceptor(tokenHolder, BuildConfig.DESIGNER_NEWS_CLIENT_ID)
                )
                .addInterceptor(loggingInterceptor)
                .build()
        return Retrofit.Builder()
                .baseUrl(DesignerNewsService.ENDPOINT)
                .client(client)
                .addConverterFactory(DenvelopingConverter(gson))
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .build()
                .create(DesignerNewsService::class.java)
    }

    /*@Provides
    fun provideLoggedInUserDao(context: Context): LoggedInUserDao {
        return DesignerNewsDatabase.getInstance(context).loggedInUserDao()
    }*/

    @Provides
    fun provideStoriesRepository(
            storiesRemoteDataSource: StoriesRemoteDataSource
    ): StoriesRepository =
            StoriesRepository.getInstance(storiesRemoteDataSource)

    @Provides
    fun provideStoriesRemoteDataSource(service: DesignerNewsService): StoriesRemoteDataSource =
            StoriesRemoteDataSource.getInstance(service)

    @Provides
    fun provideLoadStoriesUseCase(
            storiesRepository: StoriesRepository,
            coroutinesDispatcherProvider: CoroutinesDispatcherProvider
    ): LoadStoriesUseCase =
            LoadStoriesUseCase(storiesRepository, coroutinesDispatcherProvider)

    @Provides
    fun provideSearchStoriesUseCase(
            storiesRepository: StoriesRepository,
            coroutinesDispatcherProvider: CoroutinesDispatcherProvider
    ): SearchStoriesUseCase =
            SearchStoriesUseCase(storiesRepository, coroutinesDispatcherProvider)

    /*@Provides
    fun provideCommentsRepository(dataSource: CommentsRemoteDataSource): CommentsRepository =
            CommentsRepository.getInstance(dataSource)*/

    @Provides
    fun provideGson(): Gson = Gson()

}