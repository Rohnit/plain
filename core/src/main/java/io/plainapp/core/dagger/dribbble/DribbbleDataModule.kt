package io.plainapp.core.dagger.dribbble

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import dagger.Module
import dagger.Provides
import io.plainapp.core.BuildConfig
import io.plainapp.core.dagger.CoroutinesDispatcherProviderModule
import io.plainapp.core.data.CoroutinesDispatcherProvider
import io.plainapp.core.dribbble.data.ShotsRepository
import io.plainapp.core.dribbble.data.search.DribbbleSearchConverter
import io.plainapp.core.dribbble.data.search.DribbbleSearchService
import io.plainapp.core.dribbble.data.search.SearchRemoteDataSource
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Converter
import retrofit2.Retrofit

/**
 * Dagger module providing classes required to dribbble with data.
 */
@Module(includes = [CoroutinesDispatcherProviderModule::class])
class DribbbleDataModule {

    @Provides
    fun provideShotsRepository(
            remoteDataSource: SearchRemoteDataSource,
            dispatcherProvider: CoroutinesDispatcherProvider
    ) = ShotsRepository.getInstance(remoteDataSource, dispatcherProvider)

    @Provides
    fun provideDribbbleSearchService(): DribbbleSearchService =
            provideRetrofit(
                    DribbbleSearchService.ENDPOINT,
                    provideDribbleSearchConverterFactory()
            )
                    .create(DribbbleSearchService::class.java)

    @Provides
    fun provideRetrofit(
            baseUrl: String,
            factory: Converter.Factory
    ): Retrofit {
        return provideRetrofitBuilder()
                .baseUrl(baseUrl)
                .addConverterFactory(factory)
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .client(provideOkHttpClient(provideLoggingInterceptor()))
                .build()
    }

    @Provides
    fun provideRetrofitBuilder() = Retrofit.Builder()

    @Provides
    fun provideDribbleSearchConverterFactory() = DribbbleSearchConverter.Factory()

    @Provides
    fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply {
            level = if (BuildConfig.DEBUG) {
                HttpLoggingInterceptor.Level.BODY
            } else {
                HttpLoggingInterceptor.Level.NONE
            }
        }
    }

    @Provides
    fun provideOkHttpClient(loggingInterceptor: HttpLoggingInterceptor): OkHttpClient =
            OkHttpClient.Builder().addInterceptor(loggingInterceptor).build()

}