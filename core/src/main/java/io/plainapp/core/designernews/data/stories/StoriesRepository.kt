package io.plainapp.core.designernews.data.stories

class StoriesRepository(private val remoteDataSource: StoriesRemoteDataSource) {


    companion object {
        @Volatile
        private var INSTANCE: StoriesRepository? = null

        fun getInstance(remoteDataSource: StoriesRemoteDataSource): StoriesRepository {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: StoriesRepository(remoteDataSource).also {
                    INSTANCE = it
                }
            }
        }
    }

}