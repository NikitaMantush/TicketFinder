package com.nikitamantush.ticketfinder.domain.model

import com.google.gson.annotations.SerializedName

data class TicketOfferResponse(
    @SerializedName("tickets_offers")
    val ticketsOffers: List<TicketOffer>
)