package com.nikitamantush.ticketfinder.presentation.ui.country_selected.adapter.straight_rails

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.nikitamantush.ticketfinder.databinding.ItemStraightRailsBinding
import com.nikitamantush.ticketfinder.presentation.model.TicketOfferUIModel

class StraightRailsAdapter :
    ListAdapter<TicketOfferUIModel, StraightRailsViewHolder>(object : DiffUtil.ItemCallback<TicketOfferUIModel>() {
        override fun areItemsTheSame(
            oldItem: TicketOfferUIModel,
            newItem: TicketOfferUIModel
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: TicketOfferUIModel,
            newItem: TicketOfferUIModel
        ): Boolean {
            return oldItem == newItem
        }
    }) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StraightRailsViewHolder {
        return StraightRailsViewHolder(
            ItemStraightRailsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: StraightRailsViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}
