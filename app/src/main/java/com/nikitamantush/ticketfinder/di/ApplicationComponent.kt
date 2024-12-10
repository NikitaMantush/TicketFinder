package com.nikitamantush.ticketfinder.di

import com.nikitamantush.ticketfinder.presentation.ui.country_selected.CountrySelectedFragment
import com.nikitamantush.ticketfinder.presentation.ui.home.ExploreFragment
import com.nikitamantush.ticketfinder.presentation.ui.tickets.TicketsFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [NetworkModule::class, RepositoryModule::class, AppModule::class])
interface ApplicationComponent {
    fun inject(fragment: ExploreFragment)
    fun inject(fragment: CountrySelectedFragment)
    fun inject(fragment: TicketsFragment)
}