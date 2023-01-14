package com.setbitzero.dmovies.util

import androidx.recyclerview.widget.DiffUtil
import com.setbitzero.dmovies.model.Result

//compare old and new data to optimize recyclerview
class DiffUtilCallBack: DiffUtil.ItemCallback<Result>() {
    override fun areItemsTheSame(oldItem: Result, newItem: Result): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Result, newItem: Result): Boolean {
        return oldItem == newItem
    }
}