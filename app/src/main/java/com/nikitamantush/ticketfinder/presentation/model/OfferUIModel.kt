package com.nikitamantush.ticketfinder.presentation.model

data class OfferUIModel(
    val id: Int,
    val name: String,
    val town: String,
    val price: Int,
    val imageRes: Int?
)