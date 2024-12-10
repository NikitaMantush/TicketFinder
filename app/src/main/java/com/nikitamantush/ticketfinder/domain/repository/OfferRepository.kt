package com.nikitamantush.ticketfinder.domain.repository

import com.nikitamantush.ticketfinder.domain.model.OfferResponse
import com.nikitamantush.ticketfinder.domain.model.TicketOfferResponse
import com.nikitamantush.ticketfinder.domain.model.TicketResponse

interface OfferRepository {
    suspend fun getOfferList(): Result<OfferResponse>
    suspend fun getTicketOfferList(): Result<TicketOfferResponse>
    suspend fun getTicketList(): Result<TicketResponse>
}