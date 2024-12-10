package com.nikitamantush.ticketfinder.presentation.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.nikitamantush.ticketfinder.R
import com.nikitamantush.ticketfinder.presentation.util.openFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportFragmentManager.openFragment(MainFragment())

    }
}
