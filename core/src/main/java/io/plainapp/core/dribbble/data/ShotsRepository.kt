package io.plainapp.core.dribbble.data

import io.plainapp.core.data.CoroutinesDispatcherProvider
import io.plainapp.core.dribbble.data.api.model.Shot
import io.plainapp.core.dribbble.data.search.SearchRemoteDataSource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancelChildren

class ShotsRepository constructor(
        private val remoteDataSource: SearchRemoteDataSource,
        private val dispatcherProvider: CoroutinesDispatcherProvider
) {

    private val parentJob = Job()
    private val scope = CoroutineScope(dispatcherProvider.main + parentJob)

    private val inflight = mutableMapOf<String, Job>()
    private val shotCache = mutableMapOf<Long, Shot>()



    fun cancelAllSearches() {
        parentJob.cancelChildren()
        inflight.clear()
    }


    private fun cache(shots: List<Shot>) {
        shots.associateTo(shotCache) { it.id to it }
    }

    companion object {
        @Volatile
        private var INSTANCE: ShotsRepository? = null

        fun getInstance(
                remoteDataSource: SearchRemoteDataSource,
                dispatcherProvider: CoroutinesDispatcherProvider
        ): ShotsRepository {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: ShotsRepository(remoteDataSource, dispatcherProvider)
                        .also { INSTANCE = it }
            }
        }
    }

}