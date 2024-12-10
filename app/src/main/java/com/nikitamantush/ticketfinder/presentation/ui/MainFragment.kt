package com.nikitamantush.ticketfinder.presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationMenuView
import com.google.android.material.navigation.NavigationBarView
import com.nikitamantush.ticketfinder.R
import com.nikitamantush.ticketfinder.databinding.FragmentMainBinding
import com.nikitamantush.ticketfinder.presentation.ui.home.ExploreFragment
import com.nikitamantush.ticketfinder.presentation.util.openFragmentWithBottomMenu


class MainFragment: Fragment() {

    private var binding: FragmentMainBinding? = null


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainBinding.inflate(inflater)
        return binding?.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val bottomNavigationView  = binding?.bottomNavigationView
        bottomNavigationView?.labelVisibilityMode = NavigationBarView.LABEL_VISIBILITY_LABELED
        val menuView = bottomNavigationView?.getChildAt(0)
        menuView?.animation = null


        childFragmentManager
            .openFragmentWithBottomMenu(ExploreFragment())
        binding?.bottomNavigationView?.setOnItemSelectedListener { menuItem ->
            when(menuItem.itemId){
                R.id.search ->{
                    childFragmentManager
                        .openFragmentWithBottomMenu(ExploreFragment())
                    return@setOnItemSelectedListener true
                }
                R.id.hotel ->{
                    childFragmentManager
                        .openFragmentWithBottomMenu(ExploreFragment())
                    return@setOnItemSelectedListener true
                }
                R.id.point ->{
                    childFragmentManager
                        .openFragmentWithBottomMenu(ExploreFragment())
                    return@setOnItemSelectedListener true
                }
                R.id.subscriptions ->{
                    childFragmentManager
                        .openFragmentWithBottomMenu(ExploreFragment())
                    return@setOnItemSelectedListener true
                }
                R.id.profile ->{
                    childFragmentManager
                        .openFragmentWithBottomMenu(ExploreFragment())
                    return@setOnItemSelectedListener true
                }
                else ->{
                    return@setOnItemSelectedListener false
                }
            }
        }
    }
}