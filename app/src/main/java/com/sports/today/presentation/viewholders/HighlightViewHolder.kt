package com.sports.today.presentation.viewholders

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sports.today.databinding.ListItemBinding
import com.sports.today.domain.entities.Highlight

class HighlightViewHolder(private val binding: ListItemBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(item: Highlight, clickListener: (Highlight) -> Unit) {
        binding.highlight = item
        binding.root.setOnClickListener {
            binding.highlight?.apply { clickListener(this) }
        }
        binding.executePendingBindings()
    }

    companion object {
        fun from(parent: ViewGroup): HighlightViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = ListItemBinding.inflate(layoutInflater, parent, false)
            return HighlightViewHolder(binding)
        }
    }
}