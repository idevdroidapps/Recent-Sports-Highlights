package com.sports.today.presentation.adapters

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.sports.today.domain.entities.Sport
import com.sports.today.presentation.viewholders.SportViewHolder

class SportsListAdapter(private val clickListener: (Sport) -> Unit) :
    ListAdapter<Sport, RecyclerView.ViewHolder>(HIGHLIGHT_COMPARATOR) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return SportViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = getItem(position)
        item?.let {
            (holder as SportViewHolder).bind(it, clickListener)
        }
    }

    companion object {
        private val HIGHLIGHT_COMPARATOR = object : DiffUtil.ItemCallback<Sport>() {
            override fun areItemsTheSame(oldItem: Sport, newItem: Sport): Boolean =
                oldItem === newItem

            override fun areContentsTheSame(oldItem: Sport, newItem: Sport): Boolean =
                oldItem.published == newItem.published
        }
    }
}