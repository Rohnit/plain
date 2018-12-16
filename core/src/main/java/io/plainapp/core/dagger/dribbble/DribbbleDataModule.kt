package io.plainapp.core.dagger.dribbble

import dagger.Module
import io.plainapp.core.dagger.CoroutinesDispatcherProviderModule

/**
 * Dagger module providing classes required to dribbble with data.
 */
@Module(includes = [CoroutinesDispatcherProviderModule::class])
class DribbbleDataModule {
}