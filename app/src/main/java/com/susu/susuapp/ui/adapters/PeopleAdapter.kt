package com.susu.susuapp.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.susu.susuapp.databinding.ItemPeopleBinding
import com.susu.susuapp.models.EventResponse
import com.susu.susuapp.models.Result
import com.susu.susuapp.util.PeopleDiffUtils

class PeopleAdapter : RecyclerView.Adapter<PeopleAdapter.PeopleViewHolder>() {

    private var people = emptyList<Result>()

    class PeopleViewHolder(private val binding: ItemPeopleBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(result: Result) {

            with(binding) {
                ivAvatar.load(result.images!![0].image) {
                    crossfade(true)
                    crossfade(600)
                }
                tvName.text = result.title
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

    fun setData(newData: EventResponse) {
        val peopleDiffUtil =
            PeopleDiffUtils(oldList = people, newList = newData.results ?: emptyList())
        val diffUtilsResult = DiffUtil.calculateDiff(peopleDiffUtil)
        people = newData.results ?: emptyList()
        diffUtilsResult.dispatchUpdatesTo(this)
    }
}