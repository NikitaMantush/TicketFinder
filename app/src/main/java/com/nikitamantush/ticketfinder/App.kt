package com.nikitamantush.ticketfinder

import android.app.Application
import com.nikitamantush.ticketfinder.di.AppModule
import com.nikitamantush.ticketfinder.di.ApplicationComponent
import com.nikitamantush.ticketfinder.di.DaggerApplicationComponent

class App: Application() {

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerApplicationComponent.builder()
            .appModule(AppModule(this))
            .build()
    }
    companion object {
        lateinit var appComponent: ApplicationComponent
    }
}