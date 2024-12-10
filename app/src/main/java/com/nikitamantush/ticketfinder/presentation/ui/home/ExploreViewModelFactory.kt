package com.nikitamantush.ticketfinder.presentation.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.nikitamantush.ticketfinder.domain.repository.OfferRepository

class ExploreViewModelFactory(private val repository: OfferRepository)
    : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ExploreViewModel(repository) as T
    }
}