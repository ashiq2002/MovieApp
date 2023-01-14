package com.setbitzero.dmovies.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.setbitzero.dmovies.databinding.AllItemBinding
import com.setbitzero.dmovies.interfaces.OnMovieClickListener
import com.setbitzero.dmovies.model.Result
import com.setbitzero.dmovies.util.DiffUtilCallBack
import com.squareup.picasso.Picasso

class ViewAllAdapter(private val listener: OnMovieClickListener):ListAdapter<Result, ViewAllAdapter.ViewAllViewHolder>(DiffUtilCallBack()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewAllViewHolder {
        return ViewAllViewHolder(AllItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ViewAllViewHolder, position: Int) {
        val result:Result = getItem(position)
        holder.bind(result)

        holder.binding.parent.setOnClickListener{
            listener.onMovieClick(result)
        }
    }

    class ViewAllViewHolder(val binding: AllItemBinding):ViewHolder(binding.root){

        //set data into views
        fun bind(result: Result){
            binding.mvTitle.text = result.original_title
            binding.mvOverView.text = result.overview
            val path = "https://image.tmdb.org/t/p/w500/"+result.poster_path
            Picasso.get().load(path).into(binding.moviePoster)
        }
    }

}