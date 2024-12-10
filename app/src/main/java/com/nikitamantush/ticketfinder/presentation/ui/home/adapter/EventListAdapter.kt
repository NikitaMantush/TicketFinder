package com.nikitamantush.ticketfinder.presentation.ui.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.nikitamantush.ticketfinder.databinding.ItemEventBinding
import com.nikitamantush.ticketfinder.presentation.model.OfferUIModel

class EventListAdapter :
    ListAdapter<OfferUIModel, EventViewHolder>(object : DiffUtil.ItemCallback<OfferUIModel>() {
        override fun areItemsTheSame(
            oldItem: OfferUIModel,
            newItem: OfferUIModel
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: OfferUIModel,
            newItem: OfferUIModel
        ): Boolean {
            return oldItem == newItem
        }
    }) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventViewHolder {
        return EventViewHolder(
            ItemEventBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: EventViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}
