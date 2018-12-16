package io.plainapp.dagger

import dagger.Component
import io.plainapp.core.dagger.BaseComponent
import io.plainapp.core.dagger.CoreComponent
import io.plainapp.core.dagger.DataManagerModule
import io.plainapp.core.dagger.FilterAdapterModule
import io.plainapp.core.dagger.OnDataLoadedModule
import io.plainapp.core.dagger.SharedPreferencesModule
import io.plainapp.ui.HomeActivity

/**
 * Dagger component for the [HomeActivity].
 */
@Component(modules = [HomeModule::class], dependencies = [CoreComponent::class])
interface HomeComponent : BaseComponent<HomeActivity> {

    @Component.Builder
    interface Builder {

        fun build(): HomeComponent
        fun coreComponent(module: CoreComponent): Builder
        fun dataManagerModule(module: DataManagerModule): Builder
        fun dataLoadedModule(module: OnDataLoadedModule): Builder
        fun filterAdapterModule(module: FilterAdapterModule): Builder
        fun sharedPreferencesModule(module: SharedPreferencesModule): Builder
        fun homeModule(module: HomeModule): Builder
    }
}