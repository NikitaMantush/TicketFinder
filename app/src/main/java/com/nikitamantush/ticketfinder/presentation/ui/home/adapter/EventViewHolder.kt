package com.nikitamantush.ticketfinder.presentation.ui.home.adapter

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.nikitamantush.ticketfinder.databinding.ItemEventBinding
import com.nikitamantush.ticketfinder.presentation.model.OfferUIModel

class EventViewHolder (
    private val binding: ItemEventBinding
    ): RecyclerView.ViewHolder(binding.root) {
        fun bind(
            offer: OfferUIModel){

            binding.personName.text = offer.name

            binding.personPhoto.run {
                Glide.with(context)
                    .load(offer.imageRes)
                    .into(this)
            }
           binding.place.text = offer.town
           binding.price.text = "${offer.price} ₽"
        }
    }