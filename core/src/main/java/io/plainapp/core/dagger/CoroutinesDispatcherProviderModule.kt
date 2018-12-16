package io.plainapp.core.dagger

import dagger.Module
import dagger.Provides
import io.plainapp.core.data.CoroutinesDispatcherProvider
import kotlinx.coroutines.Dispatchers

@Module
class CoroutinesDispatcherProviderModule {

    @Provides
    fun provideCoroutinesDispatcherProvider() = CoroutinesDispatcherProvider(
            Dispatchers.Main,
            Dispatchers.Default,
            Dispatchers.IO
    )

}