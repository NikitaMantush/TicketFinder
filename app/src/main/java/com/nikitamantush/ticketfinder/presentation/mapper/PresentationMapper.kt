package com.nikitamantush.ticketfinder.presentation.mapper

import com.nikitamantush.ticketfinder.R
import com.nikitamantush.ticketfinder.domain.model.Offer
import com.nikitamantush.ticketfinder.domain.model.Ticket
import com.nikitamantush.ticketfinder.domain.model.TicketOffer
import com.nikitamantush.ticketfinder.presentation.model.OfferUIModel
import com.nikitamantush.ticketfinder.presentation.model.TicketOfferUIModel
import com.nikitamantush.ticketfinder.presentation.model.TicketUIModel

fun getIconForItem(id: Int): Int {
    return when (id) {
        1 -> R.drawable.ic_red_circle
        10 -> R.drawable.ic_blue_circle
        30 -> R.drawable.ic_white_circle
        else -> R.drawable.ic_red_circle
    }
}

fun getImageResource(title: String): Int {
    return when (title) {
        "Die Antwoord" -> R.drawable.die_antwoord
        "Socrat&Lera" -> R.drawable.socrat_lera
        "Лампабикт" -> R.drawable.lampabikt
        else -> R.drawable.lampabikt
    }
}
fun Offer.toOfferUIModel(): OfferUIModel {
    return OfferUIModel(
        id = id ,
        name = title,
        town = town,
        price = price.value,
        imageRes = getImageResource(title)
    )
}

fun TicketOffer.toTicketOfferUIModel(): TicketOfferUIModel {
    return TicketOfferUIModel(
        id = id ,
        title = title,
        timeRange = timeRange,
        price = price.value,
        iconRes = getIconForItem(id)
    )
}


fun Ticket.toTicketUIModel(): TicketUIModel {
    return TicketUIModel(
        id = id ,
        badge = badge,
        price = price.value,
        departureDate = departure.date,
        arrivalDate = arrival.date,
        departureAirport = departure.airport,
        arrivalAirport = arrival.airport,
        hasTransfer = hasTransfer
    )
}


