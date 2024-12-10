package com.nikitamantush.ticketfinder.presentation.ui.tickets.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.nikitamantush.ticketfinder.R
import com.nikitamantush.ticketfinder.databinding.ItemTicketBinding
import com.nikitamantush.ticketfinder.presentation.model.TicketUIModel
import java.text.SimpleDateFormat
import java.util.Locale


class TicketsViewHolder(
    private val binding: ItemTicketBinding
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(
        ticket: TicketUIModel
    ) {
        binding.run {
            if (!ticket.badge.isNullOrEmpty()) {
                badgeTextView.text = ticket.badge
                badgeTextView.setBackgroundResource(R.drawable.bg_badge)
                badgeTextView.visibility = View.VISIBLE
            }
            priceTextView.text = "${ticket.price} ₽"
            departureTimeTextView.text = getTimeFromDate(ticket.departureDate)
            departureAirportTextView.text = ticket.departureAirport
            arrivalTimeTextView.text = getTimeFromDate(ticket.arrivalDate)
            arrivalAirportTextView.text = ticket.arrivalAirport
            flightTimeTextView.text =
                calculateFlightDuration(ticket.departureDate, ticket.arrivalDate)

            if (ticket.hasTransfer) {
                slashTextView.visibility = View.VISIBLE
                hasTransferTextView.visibility = View.VISIBLE
            }
        }
    }

    private fun getTimeFromDate(input: String): String {
        val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.getDefault())
        val outputFormat = SimpleDateFormat("HH:mm", Locale.getDefault())
        val date = inputFormat.parse(input)
        return outputFormat.format(date ?: return "")
    }

    private fun calculateFlightDuration(departureDate: String, arrivalDate: String): String {
        val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.getDefault())

        val departure = inputFormat.parse(departureDate)
        val arrival = inputFormat.parse(arrivalDate)

        if (departure != null && arrival != null) {
            val durationInMillis = arrival.time - departure.time

            val hours = (durationInMillis / (1000 * 60 * 60)).toInt()
            val minutes = ((durationInMillis % (1000 * 60 * 60)) / (1000 * 60)).toInt()

            val totalHours = hours + minutes / 60.0
            return String.format(Locale.US, "%.1fч", totalHours)
        }
        return "0ч"
    }



}