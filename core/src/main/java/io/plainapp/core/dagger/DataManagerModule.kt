package io.plainapp.core.dagger

import dagger.Module
import dagger.Provides
import io.plainapp.core.dagger.designernews.DesignerNewsDataModule
import io.plainapp.core.dagger.dribbble.DribbbleDataModule
import io.plainapp.core.data.BaseDataManager
import io.plainapp.core.data.DataLoadingSubject
import io.plainapp.core.data.DataManager
import io.plainapp.core.data.PlaidItem
import io.plainapp.core.designernews.domain.LoadStoriesUseCase
import io.plainapp.core.designernews.domain.SearchStoriesUseCase
import io.plainapp.core.dribbble.data.ShotsRepository
import io.plainapp.core.ui.FilterAdapter

@Module(includes = [DribbbleDataModule::class, DesignerNewsDataModule::class])
class DataManagerModule {

    private lateinit var manager: DataManager

    @Provides
    fun provideDataManager(
            onDataLoadedCallback: BaseDataManager.OnDataLoadedCallback<List<PlaidItem>>,
            loadStoriesUseCase: LoadStoriesUseCase,
            searchStoriesUseCase: SearchStoriesUseCase,
            shotsRepository: ShotsRepository,
            filterAdapter: FilterAdapter
    ): DataManager = getDataManager(
            onDataLoadedCallback,
            loadStoriesUseCase,
            searchStoriesUseCase,
            shotsRepository,
            filterAdapter
    )


    @Provides
    fun provideDataLoadingSubject(
            onDataLoadedCallback: BaseDataManager.OnDataLoadedCallback<List<PlaidItem>>,
            loadStoriesUseCase: LoadStoriesUseCase,
            searchStoriesUseCase: SearchStoriesUseCase,
            shotsRepository: ShotsRepository,
            filterAdapter: FilterAdapter
    ): DataLoadingSubject = getDataManager(
            onDataLoadedCallback,
            loadStoriesUseCase,
            searchStoriesUseCase,
            shotsRepository,
            filterAdapter
    )


    private fun getDataManager(
            onDataLoadedCallback: BaseDataManager.OnDataLoadedCallback<List<PlaidItem>>,
            loadStoriesUseCase: LoadStoriesUseCase,
            searchStoriesUseCase: SearchStoriesUseCase,
            shotsRepository: ShotsRepository,
            filterAdapter: FilterAdapter
    ): DataManager {
        return if (::manager.isInitialized) {
            manager
        } else {
            manager = DataManager(
                    onDataLoadedCallback,
                    loadStoriesUseCase,
                    searchStoriesUseCase,
                    shotsRepository,
                    filterAdapter
            )
            manager
        }
    }

}