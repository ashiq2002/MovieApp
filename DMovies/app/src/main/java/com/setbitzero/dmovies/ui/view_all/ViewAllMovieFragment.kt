package com.setbitzero.dmovies.ui.view_all

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.setbitzero.dmovies.MovieApplication
import com.setbitzero.dmovies.R
import com.setbitzero.dmovies.adapter.ViewAllAdapter
import com.setbitzero.dmovies.databinding.FragmentSearchResultBinding
import com.setbitzero.dmovies.databinding.FragmentViewAllMovieBinding
import com.setbitzero.dmovies.factory.HomeViewModelFactory
import com.setbitzero.dmovies.interfaces.OnMovieClickListener
import com.setbitzero.dmovies.model.MovieModel
import com.setbitzero.dmovies.model.Result
import com.setbitzero.dmovies.ui.home.HomeViewModel
import com.setbitzero.dmovies.util.Movie

class ViewAllMovieFragment : Fragment(), OnMovieClickListener {
    private var _binding:FragmentViewAllMovieBinding? = null
    private val binding get() = _binding!!
    private lateinit var homeViewModel: HomeViewModel
    private var page:Int = 1
    private val args by navArgs<ViewAllMovieFragmentArgs>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        init()
        _binding = FragmentViewAllMovieBinding.inflate(inflater, container, false)

        val pages = loadData(page)

            //goto next page
        binding.nextPage.setOnClickListener{
            if(page < pages || page != pages){
                page++
                loadData(page)
            }
        }

        //back to previous page
        binding.previousPage.setOnClickListener{
            if(page > 1){
                page--
                loadData(page)
            }
        }

        return binding.root
    }


    //when view destroy binding become null value
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    //create instances
    private fun init(){
        val repository = (activity?.application as MovieApplication).repository
        homeViewModel = ViewModelProvider(this, HomeViewModelFactory(repository))[HomeViewModel::class.java]
    }

    //load all specific movie data into recyclerview
    private fun loadData(page:Int):Int{
        var pages = 0

        when(args.movies){
            Movie.POPULAR->{
                homeViewModel.getPopularMovie(page).observe(viewLifecycleOwner){
                    movieList(it)
                    pages = it.total_pages
                }
            }

            Movie.UPCOMING->{
                homeViewModel.getUpComingMovie(page).observe(viewLifecycleOwner){
                    movieList(it)
                    pages = it.total_pages
                }
            }

            Movie.NOW_PLAYING->{
                homeViewModel.getNowPlayingMovie(page).observe(viewLifecycleOwner){
                    movieList(it)
                    pages = it.total_pages
                }
            }

            Movie.TOP_RATED->{
                homeViewModel.getTopRatedMovie(page).observe(viewLifecycleOwner){
                    movieList(it)
                    pages = it.total_pages
                }
            }
            Movie.NULL->{}

        }
        return pages
    }

    //showing all popular movies
    private fun movieList(movieModel: MovieModel){
        binding.recyclerView.layoutManager = LinearLayoutManager(context)
        val adapter = ViewAllAdapter(this)
        adapter.submitList(movieModel.results)
        binding.recyclerView.adapter = adapter
    }

    override fun onMovieClick(result: Result) {
        val view: View = activity?.findViewById(R.id.toolBar)!!
        view.visibility = View.GONE
        val action = ViewAllMovieFragmentDirections.actionNavigationViewAllToNavigationDetail(result)
        findNavController().navigate(action)
    }

}