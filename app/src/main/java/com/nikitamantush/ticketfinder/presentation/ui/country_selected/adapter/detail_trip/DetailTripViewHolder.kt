package com.nikitamantush.ticketfinder.presentation.ui.country_selected.adapter.detail_trip

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.nikitamantush.ticketfinder.databinding.ItemDetailTripBinding
import com.nikitamantush.ticketfinder.presentation.model.DetailTripUIModel

class DetailTripViewHolder (
    private val binding: ItemDetailTripBinding
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(
        detail: DetailTripUIModel,
        onItemClick: (Int) -> Unit
    ) {

        binding.run {
            titleTextView.text = detail.text

            if (detail.iconResId != null) {
                iconImageView.setImageResource(detail.iconResId)
                iconImageView.visibility = View.VISIBLE
            } else {
                iconImageView.visibility = View.GONE
            }
            titleTextView.isEnabled = detail.isEnabled

            if (detail.isEnabled) {
                itemView.setOnClickListener { onItemClick(adapterPosition) }
            } else {
                itemView.setOnClickListener(null)
            }
        }
    }
}