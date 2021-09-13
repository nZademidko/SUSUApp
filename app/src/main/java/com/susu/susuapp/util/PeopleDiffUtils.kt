package com.susu.susuapp.util

import androidx.recyclerview.widget.DiffUtil
import com.susu.susuapp.models.Result

class PeopleDiffUtils(
    private val oldList: List<Result>,
    private val newList: List<Result>
) : DiffUtil.Callback() {

    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        oldList[oldItemPosition] == newList[newItemPosition]

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        oldList[oldItemPosition] == newList[newItemPosition]

}