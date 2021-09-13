package com.susu.susuapp.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.navigation.fragment.findNavController
import com.susu.susuapp.R
import com.susu.susuapp.databinding.FragmentSplashBinding
import com.susu.susuapp.ui.architecture.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashFragment : BaseFragment<FragmentSplashBinding>(R.layout.fragment_splash) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.postDelayed(
            { findNavController().navigate(R.id.action_splashFragment_to_mainFragment) },
            1000
        )
    }

    override fun getViewBinding(layoutInflater: LayoutInflater): FragmentSplashBinding =
        FragmentSplashBinding.inflate(layoutInflater)
}
