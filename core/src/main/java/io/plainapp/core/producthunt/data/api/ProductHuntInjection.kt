
@file:JvmName("ProductHuntInjection")

package io.plainapp.core.producthunt.data.api

import com.google.gson.Gson
import io.plainapp.core.BuildConfig
import io.plainapp.core.data.api.DenvelopingConverter
import io.plainapp.core.loggingInterceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

fun provideProductHuntService(): ProductHuntService {
    val client = OkHttpClient.Builder()
            .addInterceptor(AuthInterceptor(BuildConfig.PRODUCT_HUNT_DEVELOPER_TOKEN))
            .addInterceptor(loggingInterceptor)
            .build()
    val gson = Gson()
    return Retrofit.Builder()
            .baseUrl(ProductHuntService.ENDPOINT)
            .client(client)
            .addConverterFactory(DenvelopingConverter(gson))
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(ProductHuntService::class.java)
}

fun provideProductHuntRepository() = ProductHuntRepository.getInstance(provideProductHuntService())