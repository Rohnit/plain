package io.plainapp.core.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import io.plainapp.core.R
import io.plainapp.core.data.Source
import javax.inject.Inject


/**
 * Adapter for showing the list of data sources used as filters for the home grid.
 */
class FilterAdapter @Inject constructor(
        context: Context,
        val filters: MutableList<Source>
) : RecyclerView.Adapter<FilterViewHolder>()/*, FilterSwipeDismissListener*/ {

    private val context: Context = context.applicationContext
    private var callbacks: MutableList<FiltersChangedCallbacks> = ArrayList()

    val enabledSourcesCount: Int
        get() = filters.count(Source::active)

    init {
        setHasStableIds(true)
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): FilterViewHolder {
        val holder = FilterViewHolder(
                LayoutInflater.from(viewGroup.context)
                        .inflate(R.layout.filter_item, viewGroup, false)
        )
        return holder
    }

    override fun getItemCount(): Int {
        return filters.size
    }

    override fun onBindViewHolder(holder: FilterViewHolder, position: Int) {

    }


    fun registerFilterChangedCallback(callback: FiltersChangedCallbacks) {
        callbacks.add(callback)
    }

    fun unregisterFilterChangedCallback(callback: FiltersChangedCallbacks) {
        if (!callbacks.isEmpty()) {
            callbacks.remove(callback)
        }
    }


    abstract class FiltersChangedCallbacks {
        open fun onFiltersChanged(changedFilter: Source) {}

        open fun onFilterRemoved(removed: Source) {}
    }

    companion object {

        internal const val FILTER_ICON_ENABLED_ALPHA = 179 // 70%
        internal const val FILTER_ICON_DISABLED_ALPHA = 51 // 20%
    }


}


/**
 * ViewHolder for filters.
 */
class FilterViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    var filterName: TextView = itemView.findViewById(R.id.filter_name)
    var filterIcon: ImageView = itemView.findViewById(R.id.filter_icon)
    var isSwipeable: Boolean = false
}