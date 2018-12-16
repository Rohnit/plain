package io.plainapp.core.dagger.designernews

import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import io.plainapp.core.dagger.CoroutinesDispatcherProviderModule
import io.plainapp.core.dagger.SharedPreferencesModule

/**
 * Dagger module to provide data functionality for DesignerNews.
 */
@Module(includes = [SharedPreferencesModule::class, CoroutinesDispatcherProviderModule::class])
class DesignerNewsDataModule {



    @Provides
    fun provideGson(): Gson = Gson()

}