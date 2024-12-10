package com.nikitamantush.ticketfinder.presentation.ui.tickets.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.nikitamantush.ticketfinder.databinding.ItemTicketBinding
import com.nikitamantush.ticketfinder.presentation.model.TicketUIModel

class TicketsListAdapter :
    ListAdapter<TicketUIModel, TicketsViewHolder>(object : DiffUtil.ItemCallback<TicketUIModel>() {
        override fun areItemsTheSame(
            oldItem: TicketUIModel,
            newItem: TicketUIModel
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: TicketUIModel,
            newItem: TicketUIModel
        ): Boolean {
            return oldItem == newItem
        }
    }) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TicketsViewHolder {
        return TicketsViewHolder(
            ItemTicketBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: TicketsViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}
