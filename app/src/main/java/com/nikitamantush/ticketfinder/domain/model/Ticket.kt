package com.nikitamantush.ticketfinder.domain.model

import com.google.gson.annotations.SerializedName

data class Ticket(
    val id: Int,
    val badge: String? = null,
    val price: Price,
    @SerializedName("provider_name") val providerName: String,
    val company: String,
    val departure: FlightDetail,
    val arrival: FlightDetail,
    @SerializedName("has_transfer") val hasTransfer: Boolean,
    @SerializedName("has_visa_transfer") val hasVisaTransfer: Boolean,
    val luggage: Luggage,
    @SerializedName("hand_luggage") val handLuggage: HandLuggage,
    @SerializedName("is_returnable") val isReturnable: Boolean,
    @SerializedName("is_exchangable") val isExchangable: Boolean
)
