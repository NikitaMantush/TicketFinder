package com.nikitamantush.ticketfinder.domain.model

import com.google.gson.annotations.SerializedName

data class TicketOffer(
    val id: Int,
    val title: String,
    @SerializedName("time_range")
    val timeRange: List<String>,
    val price: Price
)