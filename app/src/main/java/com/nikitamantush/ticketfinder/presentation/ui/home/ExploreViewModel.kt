package com.nikitamantush.ticketfinder.presentation.ui.home

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nikitamantush.ticketfinder.domain.model.Offer
import com.nikitamantush.ticketfinder.domain.repository.OfferRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ExploreViewModel(private val repository: OfferRepository): ViewModel() {

    val offers = MutableLiveData<List<Offer>>()
    val error = MutableLiveData<String>()

    fun loadOfferList() {
        viewModelScope.launch(Dispatchers.IO) {
            Log.d("ExploreViewModel", "Start fetching offers")
            val result = repository.getOfferList()
            Log.d("ExploreViewModel", "Fetched result: $result")
            result.onSuccess { response ->
                offers.postValue(response.offers)
            }.onFailure { exception ->
                error.postValue(exception.localizedMessage)
            }
        }
    }
}