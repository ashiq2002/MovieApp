package com.setbitzero.dmovies.ui.search

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.setbitzero.dmovies.R

class SearchResultFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val query = arguments?.get("query").toString()
        Log.wtf("QUERY", query)
        return inflater.inflate(R.layout.fragment_search_result, container, false)
    }

}