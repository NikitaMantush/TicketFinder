package com.nikitamantush.ticketfinder.presentation.ui.country_selected.adapter.straight_rails

import android.text.TextUtils
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.nikitamantush.ticketfinder.databinding.ItemStraightRailsBinding
import com.nikitamantush.ticketfinder.presentation.model.TicketOfferUIModel

class StraightRailsViewHolder(
    private val binding: ItemStraightRailsBinding
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(
        offer: TicketOfferUIModel
    ) {
        val number = offer.price.toString().reversed().chunked(3).joinToString(" ").reversed()

        binding.run {

            "$number ₽".also { priceTextView.text = it }

            circleImageView.run {
                Glide.with(context)
                    .load(offer.iconRes)
                    .into(this)
            }

            timeRangeTextView.apply {
                text = offer.timeRange.joinToString(separator = " ")

                setOnClickListener {
                    toggleEllipsize()
                }
            }

            titleTextView.text = offer.title

        }

    }

    private fun toggleEllipsize() {
        with(binding.timeRangeTextView) {
            if (maxLines == 1) {
                maxLines = Integer.MAX_VALUE
                ellipsize = null
            } else {
                maxLines = 1
                ellipsize = TextUtils.TruncateAt.END
            }
        }
    }
}