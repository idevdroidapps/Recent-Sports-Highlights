package com.sports.today.presentation.adapters

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.sports.today.domain.entities.Formula1
import com.sports.today.presentation.viewholders.Formula1ViewHolder

class Formula1Adapter(private val clickListener: (View) -> Unit) :
    ListAdapter<Formula1, RecyclerView.ViewHolder>(FORMULA1_COMPARATOR) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return Formula1ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = getItem(position)
        item?.let {
            (holder as Formula1ViewHolder).bind(it, clickListener)
        }
    }

    companion object {
        private val FORMULA1_COMPARATOR = object : DiffUtil.ItemCallback<Formula1>() {
            override fun areItemsTheSame(oldItem: Formula1, newItem: Formula1): Boolean =
                oldItem === newItem

            override fun areContentsTheSame(oldItem: Formula1, newItem: Formula1): Boolean =
                oldItem.publicationDate == newItem.publicationDate
        }
    }
}