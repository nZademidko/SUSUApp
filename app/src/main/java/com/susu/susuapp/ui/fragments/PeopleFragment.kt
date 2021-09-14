package com.susu.susuapp.ui.fragments

import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import coil.load
import coil.size.Scale
import coil.transform.BlurTransformation
import coil.transform.CircleCropTransformation
import com.google.android.material.imageview.ShapeableImageView
import com.susu.susuapp.R
import com.susu.susuapp.data.peopleArr
import com.susu.susuapp.databinding.FragmentPeopleBinding
import com.susu.susuapp.ui.adapters.PeopleAdapter
import com.susu.susuapp.ui.architecture.BaseFragment
import com.susu.susuapp.ui.viewmodels.PeopleViewModel
import com.susu.susuapp.util.ColorStateListUtils
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
        getPeopleLocal()
        // getPeople()
    }

    private fun getPeopleLocal() {

        with(binding) {
            ivChairman.apply {
                load(peopleArr[0].avatarUrl) {
                    transformations(CircleCropTransformation())
                }
                strokeColor =
                    ColorStateListUtils.getColorStateList(
                        enabledColor = Color.parseColor(peopleArr[0].strokeColor)
                    )
            }
            ivToolBar
                .load("https://sun9-48.userapi.com/c824409/v824409894/14c3f7/57qBvyr1iB8.jpg") {
                   // transformations(BlurTransformation(requireContext()))
                }
            tvFaculty.apply {
                text = "ВШ ЭКН"
                setTextColor(ColorStateListUtils.getColorStateList(
                    enabledColor = Color.parseColor(peopleArr[0].strokeColor)
                ))
            }
            tvChairmanName.apply {
                text = peopleArr[0].name
                setTextColor(ColorStateListUtils.getColorStateList(
                    enabledColor = Color.parseColor(peopleArr[0].nameColor)
                ))
            }
        }

        hideShimmerEffect()
        peopleAdapter.setData(peopleArr)
    }

    private fun getPeople() {
        peopleViewModel.getPeople()
        peopleViewModel.peopleResponse.observe(viewLifecycleOwner, { response ->

            when (response) {
                is NetworkResult.Success -> {
                    hideShimmerEffect()
                    //  response.data?.let { peopleAdapter.setData(it) }
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
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            showShimmerEffect()
        }
    }

    override fun getViewBinding(layoutInflater: LayoutInflater): FragmentPeopleBinding =
        FragmentPeopleBinding.inflate(layoutInflater)
}