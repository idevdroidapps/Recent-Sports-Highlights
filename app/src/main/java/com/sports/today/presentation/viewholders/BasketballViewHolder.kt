package com.sports.today.presentation.viewholders

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sports.today.R
import com.sports.today.databinding.ListItemBinding
import com.sports.today.domain.entities.Basketball

class BasketballViewHolder(private val binding: ListItemBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(item: Basketball, clickListener: (View) -> Unit) {
        val message =
            "${item.mvp} leads ${item.winner} to a Game ${item.gameNumber} win in the ${item.tournament} "
        binding.textViewMessage.text = message
        binding.imageViewIcon.setImageResource(R.drawable.ic_basketball)
        binding.root.setOnClickListener {
            it.apply { clickListener(this) }
        }
        binding.executePendingBindings()
    }

    companion object {
        fun from(parent: ViewGroup): BasketballViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = ListItemBinding.inflate(layoutInflater, parent, false)
            return BasketballViewHolder(binding)
        }
    }
}