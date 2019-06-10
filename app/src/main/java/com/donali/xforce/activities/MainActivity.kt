package com.donali.xforce.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.get
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.donali.xforce.R
import com.donali.xforce.database.repositories.MovieInfoRepository
import com.donali.xforce.database.viewmodels.MovieInfoViewModel
import com.donali.xforce.helpers.ActivityHelper

class MainActivity : AppCompatActivity(),ActivityHelper {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        val movieInfoViewModel = ViewModelProviders.of(this).get(MovieInfoViewModel::class.java)

//        movieInfoViewModel.retreiveAllMovieList()
    }

    override fun getLayoutManager(): RecyclerView.LayoutManager  = LinearLayoutManager(this)


    override fun showEmptySearchToast()  = Toast.makeText(this,"No search param added!",Toast.LENGTH_SHORT).show()
}
