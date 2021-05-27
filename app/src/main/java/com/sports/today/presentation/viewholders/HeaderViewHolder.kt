package com.sports.today.presentation.viewholders

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sports.today.databinding.ListHeaderBinding

class HeaderViewHolder(private val binding: ListHeaderBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(headerText: String) {
        binding.textViewSectionHeader.text = headerText
        binding.executePendingBindings()
    }

    companion object {
        fun from(parent: ViewGroup): HeaderViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = ListHeaderBinding.inflate(layoutInflater, parent, false)
            return HeaderViewHolder(binding)
        }
    }
}