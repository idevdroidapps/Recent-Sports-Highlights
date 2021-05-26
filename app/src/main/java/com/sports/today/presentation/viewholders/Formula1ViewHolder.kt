package com.sports.today.presentation.viewholders

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sports.today.R
import com.sports.today.databinding.ListItemBinding
import com.sports.today.domain.entities.Formula1

class Formula1ViewHolder(private val binding: ListItemBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(item: Formula1, clickListener: (View) -> Unit) {
        val message =
            "${item.winner} wins ${item.tournament} by ${item.seconds}"
        binding.textViewMessage.text = message
        binding.imageViewIcon.setImageResource(R.drawable.ic_formula1)
        binding.root.setOnClickListener {
            it.apply { clickListener(this) }
        }
        binding.executePendingBindings()
    }

    companion object {
        fun from(parent: ViewGroup): Formula1ViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = ListItemBinding.inflate(layoutInflater, parent, false)
            return Formula1ViewHolder(binding)
        }
    }
}