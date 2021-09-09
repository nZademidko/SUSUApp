package com.susu.susuapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.todkars.shimmer.ShimmerRecyclerView

class PeopleFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_people, container, false)
        view.findViewById<ShimmerRecyclerView>(R.id.rv).showShimmer()
        return view
    }

}