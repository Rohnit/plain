
package io.plainapp.core.data

/**
 * Base class for all model types
 */
abstract class PlaidItem(
    @Transient open val id: Long,
    @Transient open val title: String,
    @Transient open var url: String? = null
) {
    var dataSource: String? = null
    var page: Int = 0
    var weight: Float = 0F // used for sorting
    var colspan: Int = 0
}
