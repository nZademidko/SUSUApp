package com.susu.susuapp.ui.fragments

import android.view.LayoutInflater
import com.susu.susuapp.R
import com.susu.susuapp.databinding.FragmentEventsBinding
import com.susu.susuapp.ui.architecture.BaseFragment

class EventsFragment : BaseFragment<FragmentEventsBinding>(R.layout.fragment_events) {

    override fun getViewBinding(layoutInflater: LayoutInflater): FragmentEventsBinding =
        FragmentEventsBinding.inflate(layoutInflater)

}