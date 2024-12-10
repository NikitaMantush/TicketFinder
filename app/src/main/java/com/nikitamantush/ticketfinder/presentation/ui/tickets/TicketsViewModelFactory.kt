package com.nikitamantush.ticketfinder.presentation.ui.tickets

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.nikitamantush.ticketfinder.domain.repository.OfferRepository

class TicketsViewModelFactory(private val repository: OfferRepository):
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return TicketsViewModel(repository) as T
    }

}