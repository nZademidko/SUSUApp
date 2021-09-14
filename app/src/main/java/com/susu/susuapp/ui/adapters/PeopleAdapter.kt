package com.susu.susuapp.ui.adapters

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.CircleCropTransformation
import com.susu.susuapp.data.peopleArr
import com.susu.susuapp.databinding.ItemPeopleBinding
import com.susu.susuapp.models.EventResponse
import com.susu.susuapp.models.Result
import com.susu.susuapp.models.people.People
import com.susu.susuapp.util.ColorStateListUtils
import com.susu.susuapp.util.PeopleDiffUtils

class PeopleAdapter : RecyclerView.Adapter<PeopleAdapter.PeopleViewHolder>() {

    private var people = emptyList<People>()

    class PeopleViewHolder(private val binding: ItemPeopleBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(result: People) {

            with(binding) {
                ivAvatar.apply {
                    load(result.avatarUrl) {
                        crossfade(true)
                        crossfade(600)
                        transformations(CircleCropTransformation())
                    }
                    strokeColor =
                        ColorStateListUtils.getColorStateList(
                            enabledColor = Color.parseColor(result.strokeColor)
                        )
                }
                tvName.text = result.name
                cvBackground.backgroundTintList =
                    ColorStateListUtils.getColorStateList(
                        enabledColor = Color.parseColor(result.strokeColor)
                    )

                tvPosition.text = result.position
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PeopleViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val itemPersonBinding = ItemPeopleBinding.inflate(layoutInflater, parent, false)
        return PeopleViewHolder(itemPersonBinding)
    }

    override fun onBindViewHolder(holder: PeopleViewHolder, position: Int) {
        holder.bind(people[position])
    }

    override fun getItemCount(): Int = people.size

    fun setData(newData: List<People>) {
        val peopleDiffUtil =
            PeopleDiffUtils(oldList = people, newList = newData)
        val diffUtilsResult = DiffUtil.calculateDiff(peopleDiffUtil)
        people = newData
        diffUtilsResult.dispatchUpdatesTo(this)
    }
}