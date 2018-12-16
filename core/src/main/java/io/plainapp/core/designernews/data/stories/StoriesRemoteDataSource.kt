package io.plainapp.core.designernews.data.stories

import io.plainapp.core.designernews.data.api.DesignerNewsService

class StoriesRemoteDataSource(private val service: DesignerNewsService) {




    companion object {
        @Volatile
        private var INSTANCE: StoriesRemoteDataSource? = null

        fun getInstance(service: DesignerNewsService): StoriesRemoteDataSource {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: StoriesRemoteDataSource(service).also { INSTANCE = it }
            }
        }
    }

}