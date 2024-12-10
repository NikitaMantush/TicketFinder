package com.nikitamantush.ticketfinder.presentation.ui.country_selected

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nikitamantush.ticketfinder.domain.model.TicketOffer
import com.nikitamantush.ticketfinder.domain.repository.OfferRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CountrySelectedViewModel(
    private val repository: OfferRepository
): ViewModel() {

    val ticketOffer = MutableLiveData<List<TicketOffer>>()
    val error = MutableLiveData<String>()

    fun loadTicketOfferList() {
        viewModelScope.launch(Dispatchers.IO) {
            Log.d("CountrySelectedViewModel", "Start fetching offers")
            val result = repository.getTicketOfferList()
            Log.d("CountrySelectedViewModel", "Fetched result: $result")
            result.onSuccess { response ->
                ticketOffer.postValue(response.ticketsOffers)
            }.onFailure { exception ->
                error.postValue(exception.localizedMessage)
            }
        }
    }


}