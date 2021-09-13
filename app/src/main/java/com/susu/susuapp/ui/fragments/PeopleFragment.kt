package com.susu.susuapp.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.susu.susuapp.R
import com.susu.susuapp.data.peopleArr
import com.susu.susuapp.databinding.FragmentPeopleBinding
import com.susu.susuapp.ui.adapters.PeopleAdapter
import com.susu.susuapp.ui.architecture.BaseFragment
import com.susu.susuapp.ui.viewmodels.PeopleViewModel
import com.susu.susuapp.util.NetworkResult
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PeopleFragment : BaseFragment<FragmentPeopleBinding>(R.layout.fragment_people) {

    private val peopleAdapter by lazy { PeopleAdapter() }
    private lateinit var peopleViewModel: PeopleViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        peopleViewModel = ViewModelProvider(requireActivity()).get(PeopleViewModel::class.java)

        setupRecyclerView()
        getPeople()
    }

//    private fun getPeopleLocal(){
//
//        peopleAdapter.setData(peopleArr)
//    }

    private fun getPeople() {
        peopleViewModel.getPeople()
        peopleViewModel.peopleResponse.observe(viewLifecycleOwner, { response ->

            when (response) {
                is NetworkResult.Success -> {
                    hideShimmerEffect()
                    response.data?.let { peopleAdapter.setData(it) }
                }
                is NetworkResult.Error -> {
                    hideShimmerEffect()
                    Toast.makeText(
                        requireContext(),
                        response.message.toString(),
                        Toast.LENGTH_SHORT
                    ).show()
                }
                is NetworkResult.Loading -> {
                    showShimmerEffect()
                }
            }
        })
    }

    private fun showShimmerEffect() = binding.rv.showShimmer()

    private fun hideShimmerEffect() = binding.rv.hideShimmer()

    private fun setupRecyclerView() {
        with(binding.rv) {

            adapter = peopleAdapter
            layoutManager = GridLayoutManager(requireContext(), 2)
            showShimmerEffect()
        }
    }

    override fun getViewBinding(layoutInflater: LayoutInflater): FragmentPeopleBinding =
        FragmentPeopleBinding.inflate(layoutInflater)
}