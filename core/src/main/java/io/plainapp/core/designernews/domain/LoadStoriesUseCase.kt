package io.plainapp.core.designernews.domain

import io.plainapp.core.data.CoroutinesDispatcherProvider
import io.plainapp.core.designernews.data.stories.StoriesRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import javax.inject.Inject

class LoadStoriesUseCase @Inject constructor(
        private val storiesRepository: StoriesRepository,
        private val dispatcherProvider: CoroutinesDispatcherProvider
) {

    private var parentJob = Job()
    private val scope = CoroutineScope(dispatcherProvider.main + parentJob)

    private val parentJobs = mutableMapOf<String, Job>()




    fun cancelAllRequests() {
        parentJob.cancel()
    }

    fun cancelRequestOfSource(source: String) {
        parentJobs[source].apply { this?.cancel() }
    }

}