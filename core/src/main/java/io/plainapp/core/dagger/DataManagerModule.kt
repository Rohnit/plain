package io.plainapp.core.dagger

import dagger.Module
import io.plainapp.core.dagger.designernews.DesignerNewsDataModule
import io.plainapp.core.dagger.dribbble.DribbbleDataModule

@Module(includes = [DribbbleDataModule::class, DesignerNewsDataModule::class])
class DataManagerModule {
}