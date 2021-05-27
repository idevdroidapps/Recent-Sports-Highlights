package com.sports.today.presentation.adapters

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sports.today.presentation.viewholders.HeaderViewHolder

class HeaderAdapter() :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var headerText: String = ""

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return HeaderViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as HeaderViewHolder).bind(headerText)
    }

    override fun getItemCount(): Int {
        return 1
    }
}