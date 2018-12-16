package io.plainapp.core.dagger

import dagger.Component
import io.plainapp.core.dagger.designernews.DesignerNewsDataModule
import io.plainapp.core.dagger.dribbble.DribbbleDataModule

/**
 * Component providing application wide singletons.
 * To call this make use of PlaidApplication.coreComponent.
 */
@Component(
        modules = [
            DribbbleDataModule::class,
            DesignerNewsDataModule::class,
            MarkdownModule::class,
            SharedPreferencesModule::class
        ]
)
interface CoreComponent {

    @Component.Builder interface Builder {
        fun build(): CoreComponent
        fun markdownModule(module: MarkdownModule): Builder
        fun sharedPreferencesModuleModule(module: SharedPreferencesModule): Builder
    }

}