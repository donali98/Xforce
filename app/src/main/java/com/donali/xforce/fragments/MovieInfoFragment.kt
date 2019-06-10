package com.donali.xforce.fragments


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.navArgs

import com.donali.xforce.R
import com.donali.xforce.database.viewmodels.MovieInfoViewModel


class MovieInfoFragment : Fragment() {


    val args:MovieInfoFragmentArgs by navArgs()
    lateinit var itemMovieName:TextView
    lateinit var itemMoviePlot:TextView
    lateinit var itemMovieYear:TextView
    lateinit var itemMovieGenre:TextView


    lateinit var movieViewModel:MovieInfoViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_movie_info, container, false)
        movieViewModel = ViewModelProviders.of(this).get(MovieInfoViewModel::class.java)
        itemMovieName = view.findViewById(R.id.tv_item_movie_name)
        itemMoviePlot = view.findViewById(R.id.tv_item_movie_plot)
        itemMovieYear = view.findViewById(R.id.tv_item_movie_date)
        itemMovieGenre = view.findViewById(R.id.tv_item_movie_genre)
        return  view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val movieId = args.imdbId

        movieViewModel.getMovieById(movieId).observe(this, Observer {
            itemMovieName.text = it.Title
            itemMoviePlot.text = it.Plot
            itemMovieYear.text = it.Released
            itemMovieGenre.text = it.Genre

        })

    }


}
