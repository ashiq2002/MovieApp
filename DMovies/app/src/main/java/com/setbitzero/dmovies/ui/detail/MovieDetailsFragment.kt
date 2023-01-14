package com.setbitzero.dmovies.ui.detail

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.setbitzero.dmovies.MovieApplication
import com.setbitzero.dmovies.R
import com.setbitzero.dmovies.databinding.FragmentMovieDetailBinding
import com.setbitzero.dmovies.factory.MovieDetailViewModelFactory

class MovieDetailsFragment : Fragment() {
    private var _binding: FragmentMovieDetailBinding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private val args by navArgs<MovieDetailsFragmentArgs>()
    private lateinit var movieDetailViewModel: MovieDetailViewModel

    @SuppressLint("SetTextI18n")
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentMovieDetailBinding.inflate(inflater, container, false)
        init()

        //observing movie detail data
        movieDetailViewModel.movieDetail.observe(viewLifecycleOwner){
            binding.mvTitle.text = it.original_title
            binding.mvDuDateType.text = it.release_date+" | "+it.runtime+"min"
            binding.mvOverView.text = it.overview
        }

        //top back button
        binding.topBackButton.background = null
        binding.topBackButton.setOnClickListener{actionBarHide()}

        //go back home button
        binding.goBackHomeBtn.setOnClickListener{actionBarHide()}

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    //create instances
    private fun init(){
        val repository = (activity?.application as MovieApplication).repository
        movieDetailViewModel = ViewModelProvider(this, MovieDetailViewModelFactory(repository, args.movieResult.id))[MovieDetailViewModel::class.java]
    }

    //handle top back button
    private fun actionBarHide(){
        val view: View = activity?.findViewById(R.id.toolBar)!!
        view.visibility = View.VISIBLE
        findNavController().navigate(R.id.navigation_home)
    }
}

