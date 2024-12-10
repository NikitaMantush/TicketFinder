package com.nikitamantush.ticketfinder.presentation.ui.tickets

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nikitamantush.ticketfinder.domain.model.Ticket
import com.nikitamantush.ticketfinder.domain.repository.OfferRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TicketsViewModel(
    private val repository: OfferRepository

) : ViewModel() {
    val tickets = MutableLiveData<List<Ticket>>()
    val error = MutableLiveData<String>()

    fun loadTicketList() {
        viewModelScope.launch(Dispatchers.IO) {
            Log.d("TicketsViewModel", "Start fetching offers")
            val result = repository.getTicketList()
            Log.d("TicketsViewModel", "Fetched result: $result")
            result.onSuccess { response ->
                tickets.postValue(response.tickets)
            }.onFailure { exception ->
                error.postValue(exception.localizedMessage)
            }
        }
    }
}