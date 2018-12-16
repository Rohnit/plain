package io.plainapp.ui

import android.app.Application
import android.content.Context
import io.plainapp.core.dagger.CoreComponent
import io.plainapp.core.dagger.DaggerCoreComponent
import io.plainapp.core.dagger.MarkdownModule

class PlainApplication : Application() {

    private val coreComponent: CoreComponent by lazy {
        DaggerCoreComponent
                .builder()
                .markdownModule(MarkdownModule(resources.displayMetrics))
                .build()
    }

    companion object {
        @JvmStatic
        fun coreComponent(context: Context) =
                (context.applicationContext as PlainApplication).coreComponent
    }

}