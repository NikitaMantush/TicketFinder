package com.nikitamantush.ticketfinder.presentation.ui.country_selected

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.nikitamantush.ticketfinder.domain.repository.OfferRepository

class CountrySelectedViewModelFactory(private val repository: OfferRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return CountrySelectedViewModel(repository) as T
    }
}