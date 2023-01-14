package com.setbitzero.dmovies.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.setbitzero.dmovies.databinding.MovieItemBinding
import com.setbitzero.dmovies.interfaces.OnMovieClickListener
import com.setbitzero.dmovies.model.Result
import com.setbitzero.dmovies.util.DiffUtilCallBack
import com.squareup.picasso.Picasso

class MovieAdapter(private val listener:OnMovieClickListener):ListAdapter<Result, MovieAdapter.MovieViewHolder>(DiffUtilCallBack()){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        return MovieViewHolder(MovieItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val result:Result = getItem(position)
        holder.bind(result)

        //set click listener in movie item and pass data into onMovieClick()
        holder.binding.cardView.setOnClickListener{
            listener.onMovieClick(result)
        }
    }

    class MovieViewHolder(val binding: MovieItemBinding) :ViewHolder(binding.root){
        fun bind(result:Result){
            val path = "https://image.tmdb.org/t/p/w500/"+result.poster_path
            Picasso.get().load(path).into(binding.moviePoster)
        }
    }

}