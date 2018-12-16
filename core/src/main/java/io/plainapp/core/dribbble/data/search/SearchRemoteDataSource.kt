package io.plainapp.core.dribbble.data.search

import javax.inject.Inject

/**
 * Work with our fake Dribbble API to search for shots by query term.
 */
class SearchRemoteDataSource @Inject constructor(private val service: DribbbleSearchService){




    enum class SortOrder(val sort: String) {
        POPULAR(""),
        RECENT("latest")
    }

    companion object {
        @Volatile
        private var INSTANCE: SearchRemoteDataSource? = null

        fun getInstance(service: DribbbleSearchService): SearchRemoteDataSource {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: SearchRemoteDataSource(service).also { INSTANCE = it }
            }
        }
    }

}