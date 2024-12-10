package com.nikitamantush.ticketfinder.presentation.ui.country_selected.adapter.detail_trip

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.nikitamantush.ticketfinder.databinding.ItemDetailTripBinding
import com.nikitamantush.ticketfinder.presentation.model.DetailTripUIModel

class DetailTripAdapter(
    private val onItemClick: (Int) -> Unit
) : ListAdapter<DetailTripUIModel, DetailTripViewHolder>(object : DiffUtil.ItemCallback<DetailTripUIModel>() {
    override fun areItemsTheSame(
        oldItem: DetailTripUIModel,
        newItem: DetailTripUIModel
    ): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(
        oldItem: DetailTripUIModel,
        newItem: DetailTripUIModel
    ): Boolean {
        return oldItem == newItem
    }
}) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailTripViewHolder {
        return DetailTripViewHolder(
            ItemDetailTripBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: DetailTripViewHolder, position: Int) {
        holder.bind(getItem(position), onItemClick)
    }
}
