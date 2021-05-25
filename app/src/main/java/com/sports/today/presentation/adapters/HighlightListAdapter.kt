package com.sports.today.presentation.adapters

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.sports.today.domain.entities.Highlight
import com.sports.today.presentation.viewholders.HighlightViewHolder

class HighlightListAdapter(private val clickListener: (Highlight) -> Unit) :
    ListAdapter<Highlight, RecyclerView.ViewHolder>(HIGHLIGHT_COMPARATOR) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return HighlightViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = getItem(position)
        item?.let {
            (holder as HighlightViewHolder).bind(it, clickListener)
        }
    }

    companion object {
        private val HIGHLIGHT_COMPARATOR = object : DiffUtil.ItemCallback<Highlight>() {
            override fun areItemsTheSame(oldItem: Highlight, newItem: Highlight): Boolean =
                oldItem === newItem

            override fun areContentsTheSame(oldItem: Highlight, newItem: Highlight): Boolean =
                oldItem.id == newItem.id
        }
    }
}