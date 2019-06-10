package com.donali.xforce.fragments


import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView

import com.donali.xforce.R
import com.donali.xforce.database.viewmodels.MovieInfoViewModel
import com.donali.xforce.fragments.adapters.MovieInfoAdapter
import com.donali.xforce.helpers.ActivityHelper


class MovieListFragment : Fragment() {

    lateinit var movieListRecyclerView: RecyclerView
    lateinit var movieInfoAdapter: MovieInfoAdapter
    lateinit var activityHelper: ActivityHelper
    lateinit var movieInfoViewModel: MovieInfoViewModel
    lateinit var tvSearch: TextView
    lateinit var btnSearch: Button

    val clickListener = fun(view: View, imdbID: String) {
        try {
            val nextAction = MovieListFragmentDirections.nextAction()
            nextAction.imdbId = imdbID
            view.findNavController().navigate(nextAction)
        } catch (e: Exception) {
            Log.e("CUSTOM", e.toString())
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        activityHelper = context as ActivityHelper
    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_movie_list, container, false)
        movieListRecyclerView = view.findViewById(R.id.rv_list_movies)
        tvSearch = view.findViewById(R.id.tv_search)
        btnSearch = view.findViewById(R.id.btn_search)
        movieInfoAdapter = MovieInfoAdapter(clickListener)
        movieListRecyclerView.apply {
            setHasFixedSize(true)
            adapter = movieInfoAdapter
            layoutManager = activityHelper.getLayoutManager()
        }


        movieInfoViewModel = ViewModelProviders.of(this).get(MovieInfoViewModel::class.java)

        movieInfoViewModel.getAllMovieInfo().observe(this, Observer {
            if (it.isEmpty()) {
                /*
                  TODO: Verificar si tiene internet
                * */
                movieInfoViewModel.retreiveAllMovieList()
            } else {
                movieInfoAdapter.setData(it)
            }

        })

        btnSearch.setOnClickListener {
            var searchParam = tvSearch.text
            if(searchParam.isEmpty()) activityHelper.showEmptySearchToast()
            else{
                searchParam = addChar(searchParam.toString(),'%',0)
                searchParam = addChar(searchParam.toString(),'%',searchParam.toString().length)
//                Log.d("CUSTOM",searchParam)
                movieInfoViewModel.findMoviesByTitle(searchParam.toString()).observe(this, Observer {
                    movieInfoAdapter.setData(it)
                })
            }
        }

        return view
    }
    fun addChar(str: String, ch: Char, position: Int): String {
        val sb = StringBuilder(str)
        sb.insert(position, ch)
        return sb.toString()
    }

}
