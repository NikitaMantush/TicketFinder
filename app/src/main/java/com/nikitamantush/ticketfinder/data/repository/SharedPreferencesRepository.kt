package com.nikitamantush.ticketfinder.data.repository

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit
import javax.inject.Singleton

private const val SHARED_PREF = "sharedPref"
private const val DEPARTURE_FROM = "departureFrom"

@Singleton
class SharedPreferencesRepository(context: Context) {

    private val sharedPreferences: SharedPreferences

    init {
        sharedPreferences = context.getSharedPreferences(SHARED_PREF, Context.MODE_PRIVATE)
    }
    fun setDepartureFrom(departureFrom: String) {
        sharedPreferences.edit {
            putString(DEPARTURE_FROM, departureFrom)
        }
    }
    fun getDepartureFrom(): String? {
        return sharedPreferences.getString(DEPARTURE_FROM, null)
    }

}