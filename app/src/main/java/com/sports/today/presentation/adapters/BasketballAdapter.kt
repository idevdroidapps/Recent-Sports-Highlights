package com.sports.today.presentation.adapters

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.sports.today.domain.entities.Basketball
import com.sports.today.presentation.viewholders.BasketballViewHolder

class BasketballAdapter(private val clickListener: (View) -> Unit) :
    ListAdapter<Basketball, RecyclerView.ViewHolder>(BASKETBALL_COMPARATOR) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return BasketballViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = getItem(position)
        item?.let {
            (holder as BasketballViewHolder).bind(it, clickListener)
        }
    }

    companion object {
        private val BASKETBALL_COMPARATOR = object : DiffUtil.ItemCallback<Basketball>() {
            override fun areItemsTheSame(oldItem: Basketball, newItem: Basketball): Boolean =
                oldItem === newItem

            override fun areContentsTheSame(oldItem: Basketball, newItem: Basketball): Boolean =
                oldItem.publicationDate == newItem.publicationDate
        }
    }
}