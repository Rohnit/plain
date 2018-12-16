package io.plainapp.core.dagger

import dagger.Module
import dagger.Provides
import io.plainapp.core.data.BaseDataManager
import io.plainapp.core.data.PlaidItem

@Module
class OnDataLoadedModule(
        private val callback: BaseDataManager.OnDataLoadedCallback<List<PlaidItem>>
) {

    @Provides
    fun provideOnDataLoadedCallback() = callback

}