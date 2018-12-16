package io.plainapp.core.dribbble.data.search

import androidx.annotation.StringDef

/**
 * Fake-API for searching dribbble
 */
interface DribbbleSearchService {



    @kotlin.annotation.Retention(AnnotationRetention.SOURCE)
    @StringDef(
            SORT_POPULAR,
            SORT_RECENT
    )
    annotation class SortOrder

    companion object {

        const val ENDPOINT = "https://dribbble.com/"
        const val SORT_POPULAR = ""
        const val SORT_RECENT = "latest"
        const val PER_PAGE_DEFAULT = 12
    }

}