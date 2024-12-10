package com.nikitamantush.ticketfinder.data.network

import com.nikitamantush.ticketfinder.domain.model.OfferResponse
import com.nikitamantush.ticketfinder.domain.model.TicketOfferResponse
import com.nikitamantush.ticketfinder.domain.model.TicketResponse
import retrofit2.Response
import retrofit2.http.GET

interface Api {

    @GET("068685c7-c74e-4b85-abed-5249862334ea")
    suspend fun getOfferList(): Response<OfferResponse>

    @GET("6fc141a6-fe22-4026-829a-540332e24618")
    suspend fun getTicketOfferList(): Response<TicketOfferResponse>

    @GET("7c8d45ff-1635-41e4-a243-37a6120e429f")
    suspend fun getTicketList(): Response<TicketResponse>
}