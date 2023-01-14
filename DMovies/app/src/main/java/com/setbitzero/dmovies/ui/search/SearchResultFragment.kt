package com.setbitzero.dmovies.ui.search

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.setbitzero.dmovies.MovieApplication
import com.setbitzero.dmovies.R
import com.setbitzero.dmovies.adapter.ViewAllAdapter
import com.setbitzero.dmovies.databinding.FragmentSearchResultBinding
import com.setbitzero.dmovies.factory.HomeViewModelFactory
import com.setbitzero.dmovies.interfaces.OnMovieClickListener
import com.setbitzero.dmovies.model.MovieModel
import com.setbitzero.dmovies.ui.home.HomeViewModel
import com.setbitzero.dmovies.model.Result

class SearchResultFragment : Fragment(), OnMovieClickListener {
    private var _binding:FragmentSearchResultBinding? = null
    private val binding get() = _binding!!
    private lateinit var homeViewModel: HomeViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentSearchResultBinding.inflate(inflater, container, false)
        val repository = (activity?.application as MovieApplication).repository
        homeViewModel = ViewModelProvider(this, HomeViewModelFactory(repository))[HomeViewModel::class.java]

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //showing custom action/toolbar
        val toolBar = view.findViewById<ConstraintLayout>(R.id.toolBar)
        toolBar.visibility = View.VISIBLE

        val query = arguments?.get("query").toString()
        if(query.isNotBlank() && query.isEmpty()){
            homeViewModel.getMovieSearchResult(query
                .trim()
                .replace(" ", "+"))
                .observe(viewLifecycleOwner){
                searchMovieList(it)
            }
        }


    }

    //set adapter from search movie result data
    private fun searchMovieList(movieModel: MovieModel){
        binding.searchResultRecView.layoutManager = LinearLayoutManager(context)
        val adapter = ViewAllAdapter(this)
        adapter.submitList(movieModel.results)
        binding.searchResultRecView.adapter = adapter
    }

    //send data current fragment to movieDetail fragment
    override fun onMovieClick(result: Result) {
        val view:View = activity?.findViewById(R.id.toolBar)!!
        view.visibility = View.GONE
        val action = SearchResultFragmentDirections.actionNavigationSearchToNavigationDetail(result)
        findNavController().navigate(action)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}