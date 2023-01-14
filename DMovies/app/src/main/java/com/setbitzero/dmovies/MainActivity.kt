package com.setbitzero.dmovies

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import com.setbitzero.dmovies.databinding.ActivityMainBinding
import com.setbitzero.dmovies.ui.search.SearchResultFragment


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.searchItem.setOnClickListener{
            binding.searchItem.visibility = View.GONE
            binding.itemNotification.visibility = View.GONE
            binding.profileImage.visibility = View.GONE
            binding.searchMovieInL.visibility = View.VISIBLE
        }

        binding.searchMovieInL.setEndIconOnClickListener {
            binding.searchMovieInL.visibility = View.GONE
            binding.searchItem.visibility=View.VISIBLE
            binding.itemNotification.visibility = View.VISIBLE
            binding.profileImage.visibility = View.VISIBLE

            val query = binding.searchMovie.text.toString()
            sendQuery(query)
        }
    }

    private fun sendQuery(query:String){
        val args = SearchResultFragment()
        val transaction = supportFragmentManager.beginTransaction()
        val bundle = Bundle()
        bundle.putString("query", query)
        args.arguments = bundle
        transaction.replace(R.id.main_activity_container, args).commit()
        findNavController(R.id.nav_controller).navigate(R.id.navigation_search)
    }

}