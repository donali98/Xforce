package com.donali.xforce.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.get
import com.donali.xforce.R
import com.donali.xforce.database.repositories.MovieInfoRepository
import com.donali.xforce.database.viewmodels.MovieInfoViewModel

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val movieInfoViewModel = ViewModelProviders.of(this).get(MovieInfoViewModel::class.java)

        movieInfoViewModel.retreiveAllMovieList()
    }
}
