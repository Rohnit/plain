package io.plainapp.core.dagger

import android.content.Context
import dagger.Module
import dagger.Provides
import io.plainapp.core.data.Source
import io.plainapp.core.data.prefs.SourceManager

@Module class FilterAdapterModule(val context: Context) {

    @Provides
    fun provideSources(): MutableList<Source> = SourceManager.getSources(context)

}