package com.setbitzero.dmovies.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.setbitzero.dmovies.MovieApplication
import com.setbitzero.dmovies.R
import com.setbitzero.dmovies.adapter.MovieAdapter
import com.setbitzero.dmovies.databinding.FragmentHomeBinding
import com.setbitzero.dmovies.factory.HomeViewModelFactory
import com.setbitzero.dmovies.interfaces.OnMovieClickListener
import com.setbitzero.dmovies.model.MovieModel
import com.setbitzero.dmovies.model.Result
import com.setbitzero.dmovies.util.Movie

class HomeFragment : Fragment(), OnMovieClickListener {
    private var _binding: FragmentHomeBinding? = null
    private lateinit var homeViewModel: HomeViewModel
    private lateinit var horizontalLayout:LinearLayoutManager

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        init()
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val view:View = activity?.findViewById(R.id.toolBar)!!
        view.visibility = View.VISIBLE

        //observing popular movie
        homeViewModel.getPopularMovie(1).observe(viewLifecycleOwner){
            for(i in it.results){
                Log.wtf("RESPONSE", i.title+"\n")
            }
            popularMovieList(it)
        }

        //observing topRated movie
        homeViewModel.getTopRatedMovie(1).observe(viewLifecycleOwner){
            topRatedMovieList(it)
        }

        //observing now playing movie
        homeViewModel.getNowPlayingMovie(1).observe(viewLifecycleOwner){
            nowPlayingMovieList(it)
        }

        //observing upcoming movie
        homeViewModel.getUpComingMovie(1).observe(viewLifecycleOwner){
            upcomingMovieList(it)
        }

        //navigate and view all popular movie
        binding.viewAllPopularMovies.setOnClickListener{
            val action = HomeFragmentDirections.actionNavigationHomeToNavigationViewAll(Movie.POPULAR)
            findNavController().navigate(action)
        }

        //navigate and view all upcoming movies
        binding.viewAllUpcomingMovie.setOnClickListener{
            val action = HomeFragmentDirections.actionNavigationHomeToNavigationViewAll(Movie.UPCOMING)
            findNavController().navigate(action)
        }

        //navigate and view all top rated movies
        binding.viewAllTopRatedMovies.setOnClickListener{
            val action = HomeFragmentDirections.actionNavigationHomeToNavigationViewAll(Movie.TOP_RATED)
            findNavController().navigate(action)
        }

        //navigate and view all now playing movies
        binding.viewAllNowPlayingMovies.setOnClickListener{
            val action = HomeFragmentDirections.actionNavigationHomeToNavigationViewAll(Movie.NOW_PLAYING)
            findNavController().navigate(action)
        }

        return binding.root
    }

    //when view destroy binding become null value
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    //creating all instances
    private fun init(){
        val repository = (activity?.application as MovieApplication).repository // get repository instance from MovieApplication class
        homeViewModel = ViewModelProvider(this, HomeViewModelFactory(repository))[HomeViewModel::class.java]
    }

    //showing all popular movies
    private fun popularMovieList(movieModel: MovieModel){
        horizontalLayout = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        binding.popularMovieRecView.layoutManager = horizontalLayout
        val adapter = MovieAdapter(this)
        adapter.submitList(movieModel.results)
        binding.popularMovieRecView.adapter = adapter
    }

    //showing all topRated movies
    private fun topRatedMovieList(movieModel: MovieModel){
        horizontalLayout = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        binding.topRatedMovieRecView.layoutManager = horizontalLayout
        val adapter = MovieAdapter(this)
        adapter.submitList(movieModel.results)
        binding.topRatedMovieRecView.adapter = adapter
    }

    //showing all topRated movies
    private fun nowPlayingMovieList(movieModel: MovieModel){
        horizontalLayout = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        binding.nowPlayingMovieRecView.layoutManager = horizontalLayout
        val adapter = MovieAdapter(this)
        adapter.submitList(movieModel.results)
        binding.nowPlayingMovieRecView.adapter = adapter
    }

    //showing all topRated movies
    private fun upcomingMovieList(movieModel: MovieModel){
        horizontalLayout = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        binding.upcomingMovieRecView.layoutManager = horizontalLayout
        val adapter = MovieAdapter(this)
        adapter.submitList(movieModel.results)
        binding.upcomingMovieRecView.adapter = adapter
    }

    //pass data this fragment to another fragment
    override fun onMovieClick(result: Result) {
        val view:View = activity?.findViewById(R.id.toolBar)!!
        view.visibility = View.GONE
        val action = HomeFragmentDirections.actionNavigationHomeToNavigationDetail(result)
        findNavController().navigate(action)
    }
}