package com.nikitamantush.ticketfinder.presentation.model

data class TicketUIModel (
    val id: Int,
    val badge: String? = null,
    val price: Int,
    val departureDate: String,
    val arrivalDate: String,
    val departureAirport: String,
    val arrivalAirport: String,
    val hasTransfer: Boolean,
)