package com.nikitamantush.ticketfinder.di

import android.content.Context
import com.nikitamantush.ticketfinder.data.network.Api
import com.nikitamantush.ticketfinder.data.repository.OfferRepositoryImpl
import com.nikitamantush.ticketfinder.domain.repository.OfferRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(private val context: Context) {

    @Provides
    @Singleton
    fun provideContext(): Context {
        return context
    }

    @Provides
    fun provideOfferRepository(api: Api): OfferRepository{
        return OfferRepositoryImpl(api)
    }
}