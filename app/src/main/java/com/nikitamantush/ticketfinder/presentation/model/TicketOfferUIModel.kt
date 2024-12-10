package com.nikitamantush.ticketfinder.presentation.model


data class TicketOfferUIModel (
    val id: Int,
    val title: String,
    val timeRange: List<String>,
    val price: Int,
    val iconRes: Int?
)