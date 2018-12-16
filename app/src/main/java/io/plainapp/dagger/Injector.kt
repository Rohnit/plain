
@file:JvmName("Injector")

package io.plainapp.dagger

import io.plainapp.core.dagger.DataManagerModule
import io.plainapp.core.dagger.FilterAdapterModule
import io.plainapp.core.dagger.OnDataLoadedModule
import io.plainapp.core.dagger.SharedPreferencesModule
import io.plainapp.core.data.BaseDataManager
import io.plainapp.core.data.PlaidItem
import io.plainapp.core.designernews.data.login.LoginLocalDataSource
import io.plainapp.ui.HomeActivity
import io.plainapp.ui.PlainApplication

/**
 * Injector for HomeActivity.
 */
fun inject(
        activity: HomeActivity,
        dataLoadedCallback: BaseDataManager.OnDataLoadedCallback<List<PlaidItem>>
) {
    DaggerHomeComponent.builder()
            .coreComponent(PlainApplication.coreComponent(activity))
            .dataManagerModule(DataManagerModule())
            .dataLoadedModule(OnDataLoadedModule(dataLoadedCallback))
            .filterAdapterModule(FilterAdapterModule(activity))
            .homeModule(HomeModule(activity))
            .sharedPreferencesModule(
                    SharedPreferencesModule(activity, LoginLocalDataSource.DESIGNER_NEWS_PREF)
            )
            .build()
            .inject(activity)
}