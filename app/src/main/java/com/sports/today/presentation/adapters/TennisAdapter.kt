package com.sports.today.presentation.adapters

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.sports.today.domain.entities.Tennis
import com.sports.today.presentation.viewholders.TennisViewHolder

class TennisAdapter(private val clickListener: (View) -> Unit) :
    ListAdapter<Tennis, RecyclerView.ViewHolder>(TENNIS_COMPARATOR) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return TennisViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = getItem(position)
        item?.let {
            (holder as TennisViewHolder).bind(it, clickListener)
        }
    }

    companion object {
        private val TENNIS_COMPARATOR = object : DiffUtil.ItemCallback<Tennis>() {
            override fun areItemsTheSame(oldItem: Tennis, newItem: Tennis): Boolean =
                oldItem === newItem

            override fun areContentsTheSame(oldItem: Tennis, newItem: Tennis): Boolean =
                oldItem.publicationDate == newItem.publicationDate
        }
    }
}