package com.nikitamantush.ticketfinder.presentation.util

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.nikitamantush.ticketfinder.R

fun FragmentManager.openFragment(fragment: Fragment) {
    this.beginTransaction()
        .replace(R.id.container, fragment)
        .commit()
}
fun FragmentManager.openFragmentWithBottomMenu(fragment: Fragment) {
    this.beginTransaction()
        .replace(R.id.child_container, fragment)
        .commit()
}
