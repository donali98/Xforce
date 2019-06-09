package com.donali.xforce.fragments


import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.RecyclerView

import com.donali.xforce.R
import com.donali.xforce.database.viewmodels.MovieInfoViewModel
import com.donali.xforce.fragments.adapters.MovieInfoAdapter
import com.donali.xforce.helpers.ActivityHelper


class MovieListFragment : Fragment() {

    lateinit var movieListRecyclerView:RecyclerView
    lateinit var movieInfoAdapter:MovieInfoAdapter
    lateinit var activityHelper:ActivityHelper
    lateinit var movieInfoViewModel: MovieInfoViewModel

    override fun onAttach(context: Context) {
        super.onAttach(context)
        activityHelper = context as ActivityHelper
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_movie_list, container, false)
        movieListRecyclerView = view.findViewById(R.id.rv_list_movies)
        movieInfoAdapter = MovieInfoAdapter {
            Log.d("CUSTOM","clicked!")
        }
        movieListRecyclerView.apply {
            setHasFixedSize(true)
            adapter = movieInfoAdapter
            layoutManager = activityHelper.getLayoutManager()
        }
        movieInfoViewModel = ViewModelProviders.of(this).get(MovieInfoViewModel::class.java)




        movieInfoViewModel.getAllMovieInfo().observe(this, Observer {
            if(it.isEmpty()){
                movieInfoViewModel.retreiveAllMovieList()
            }
            else{
                movieInfoAdapter.setData(it)
            }

        })


        return view
    }

}
