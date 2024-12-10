package com.nikitamantush.ticketfinder.data.repository

import com.nikitamantush.ticketfinder.data.network.Api
import com.nikitamantush.ticketfinder.domain.model.OfferResponse
import com.nikitamantush.ticketfinder.domain.model.TicketOfferResponse
import com.nikitamantush.ticketfinder.domain.model.TicketResponse
import com.nikitamantush.ticketfinder.domain.repository.OfferRepository
import retrofit2.Response

class OfferRepositoryImpl(private val api: Api) : OfferRepository {

    override suspend fun getOfferList(): Result<OfferResponse> = safeApiCall {
        handleApiResponse(api.getOfferList(), "Failed to fetch OfferList")
    }

    override suspend fun getTicketOfferList(): Result<TicketOfferResponse> = safeApiCall {
        handleApiResponse(api.getTicketOfferList(), "Failed to fetch TicketOfferList")
    }

    override suspend fun getTicketList(): Result<TicketResponse> = safeApiCall {
        handleApiResponse(api.getTicketList(), "Failed to fetch TicketList")
    }


    private suspend fun <T> safeApiCall(apiCall: suspend () -> T): Result<T> {
        return try {
            val result = apiCall()
            Result.success(result)
        } catch (e: Exception) {
            Result.failure(Exception("Error during API call: ${e.localizedMessage}", e))
        }
    }
    private fun <T> handleApiResponse(response: Response<T>, errorMessage: String): T {
        if (!response.isSuccessful) {
            throw Exception("$errorMessage: HTTP ${response.code()}, ${response.message()}")
        }
        return response.body() ?: throw Exception("$errorMessage: Empty response body")
    }
}