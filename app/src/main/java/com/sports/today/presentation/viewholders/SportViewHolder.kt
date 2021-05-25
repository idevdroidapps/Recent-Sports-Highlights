package com.sports.today.presentation.viewholders

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sports.today.databinding.ListItemBinding
import com.sports.today.domain.entities.Sport

class SportViewHolder(private val binding: ListItemBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(item: Sport, clickListener: (Sport) -> Unit) {
        binding.sport = item
        binding.root.setOnClickListener {
            binding.sport?.apply { clickListener(this) }
        }
        binding.executePendingBindings()
    }

    companion object {
        fun from(parent: ViewGroup): SportViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = ListItemBinding.inflate(layoutInflater, parent, false)
            return SportViewHolder(binding)
        }
    }
}