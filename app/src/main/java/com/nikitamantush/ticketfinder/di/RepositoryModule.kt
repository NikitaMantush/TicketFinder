package com.nikitamantush.ticketfinder.di

import android.content.Context
import com.nikitamantush.ticketfinder.data.repository.SharedPreferencesRepository
import com.nikitamantush.ticketfinder.domain.repository.OfferRepository
import com.nikitamantush.ticketfinder.presentation.ui.country_selected.CountrySelectedViewModelFactory
import com.nikitamantush.ticketfinder.presentation.ui.home.ExploreViewModelFactory
import com.nikitamantush.ticketfinder.presentation.ui.tickets.TicketsViewModelFactory
import dagger.Module
import dagger.Provides

@Module
class RepositoryModule {
    @Provides
    fun provideExploreViewModelFactory(repository: OfferRepository)
            : ExploreViewModelFactory {
        return ExploreViewModelFactory(repository)
    }
    @Provides
    fun provideCountrySelectedViewModelFactory(repository: OfferRepository)
            : CountrySelectedViewModelFactory {
        return CountrySelectedViewModelFactory(repository)
    }

    @Provides
    fun provideTicketsViewModelFactory(repository: OfferRepository)
            : TicketsViewModelFactory {
        return TicketsViewModelFactory(repository)
    }

    @Provides
    fun provideSharedPreferencesRepository(context: Context): SharedPreferencesRepository {
        return SharedPreferencesRepository(context)
    }
}