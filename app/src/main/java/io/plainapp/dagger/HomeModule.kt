package io.plainapp.dagger

import android.app.Activity
import android.content.Context
import com.bumptech.glide.util.ViewPreloadSizeProvider
import dagger.Module
import dagger.Provides
import io.plainapp.R
import io.plainapp.core.dagger.DataManagerModule
import io.plainapp.core.dagger.FilterAdapterModule
import io.plainapp.core.dagger.OnDataLoadedModule
import io.plainapp.core.dagger.designernews.DesignerNewsDataModule
import io.plainapp.core.dagger.dribbble.DribbbleDataModule
import io.plainapp.core.dribbble.data.api.model.Shot

@Module(
        includes = [
            DataManagerModule::class,
            DesignerNewsDataModule::class,
            DribbbleDataModule::class,
            FilterAdapterModule::class,
            OnDataLoadedModule::class
        ])
class HomeModule(private val activity: Activity) {

    @Provides
    fun context(): Context = activity

    @Provides
    fun activity(): Activity = activity

    @Provides
    fun columns(): Int = activity.resources.getInteger(R.integer.num_columns)

    @Provides
    fun viewPreloadSizeProvider(): ViewPreloadSizeProvider<Shot> = ViewPreloadSizeProvider()

}