
package io.plainapp.core.dribbble.data.api

import io.plainapp.core.data.PlaidItemSorting
import io.plainapp.core.dribbble.data.api.model.Shot

/**
 * Utility class for applying weights to a group of [Shot]s for sorting. Weighs shots relative
 * to the most liked shot in the group.
 */
class ShotWeigher : PlaidItemSorting.PlaidItemGroupWeigher<Shot> {

    override fun weigh(shots: List<Shot>) {
        // We add 1 to the max so that weights don't 'overflow' into the next page range
        val maxLikes = (shots.maxBy { it.likesCount }?.likesCount?.toFloat() ?: 0f) + 1f
        shots.forEach { shot ->
            val weight = 1f - (shot.likesCount.toFloat() / maxLikes)
            shot.weight = shot.page + weight
        }
    }
}
