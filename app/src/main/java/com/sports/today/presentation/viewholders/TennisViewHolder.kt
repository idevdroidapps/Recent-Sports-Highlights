package com.sports.today.presentation.viewholders

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sports.today.R
import com.sports.today.databinding.ListItemBinding
import com.sports.today.domain.entities.Tennis

class TennisViewHolder(private val binding: ListItemBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(item: Tennis, clickListener: (View) -> Unit) {
        val message =
            "${item.tournament}: ${item.winner} wins against ${item.looser} in ${item.numberOfSets} sets"
        binding.textViewMessage.text = message
        binding.imageViewIcon.setImageResource(R.drawable.ic_tennis)
        binding.textViewDate.text = item.publicationDate
        binding.root.setOnClickListener {
            it.apply { clickListener(this) }
        }
        binding.executePendingBindings()
    }

    companion object {
        fun from(parent: ViewGroup): TennisViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = ListItemBinding.inflate(layoutInflater, parent, false)
            return TennisViewHolder(binding)
        }
    }
}