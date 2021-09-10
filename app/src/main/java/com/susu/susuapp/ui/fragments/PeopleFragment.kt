package com.susu.susuapp.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.susu.susuapp.R
import com.susu.susuapp.databinding.FragmentPeopleBinding
import com.susu.susuapp.ui.architecture.BaseFragment
import com.todkars.shimmer.ShimmerRecyclerView

class PeopleFragment : BaseFragment<FragmentPeopleBinding>(R.layout.fragment_people) {

    override fun getViewBinding(layoutInflater: LayoutInflater): FragmentPeopleBinding =
        FragmentPeopleBinding.inflate(layoutInflater)
}