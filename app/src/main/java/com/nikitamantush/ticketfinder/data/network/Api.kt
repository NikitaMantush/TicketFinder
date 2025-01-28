package com.nikitamantush.ticketfinder.data.network

import com.nikitamantush.ticketfinder.domain.model.OfferResponse
import com.nikitamantush.ticketfinder.domain.model.TicketOfferResponse
import com.nikitamantush.ticketfinder.domain.model.TicketResponse
import retrofit2.Response
import retrofit2.http.GET

interface Api {

    @GET("94dfb7bd-3962-409f-9cca-796fd7f40189")
    suspend fun getOfferList(): Response<OfferResponse>

    @GET("4000298b-2834-4cee-b863-90c17f6e65d8")
    suspend fun getTicketOfferList(): Response<TicketOfferResponse>

    @GET("eb2a5015-6580-4ce8-8642-c4ca7b593a56")
    suspend fun getTicketList(): Response<TicketResponse>
}