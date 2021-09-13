package com.susu.susuapp.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.susu.susuapp.R
import com.susu.susuapp.databinding.FragmentMainBinding
import com.susu.susuapp.helpers.setupWithNavController
import com.susu.susuapp.ui.architecture.BaseFragment
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainFragment : BaseFragment<FragmentMainBinding>(R.layout.fragment_main) {


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.bottomNavigationView.setupWithNavController(
            navGraphIds = listOf(
                R.navigation.nav_graph_people,
                R.navigation.nav_graph_events
            ),
            fragmentManager = childFragmentManager,
            containerId = R.id.mainFragmentContainerView,
            intent = requireActivity().intent
        )

    }

    override fun getViewBinding(layoutInflater: LayoutInflater): FragmentMainBinding =
        FragmentMainBinding.inflate(layoutInflater)
}