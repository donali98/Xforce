package com.donali.xforce.fragments.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.donali.xforce.R
import com.donali.xforce.database.entities.MovieInfo
import com.squareup.picasso.Picasso

class MovieInfoAdapter (val clickListener:()->Unit):RecyclerView.Adapter<MovieInfoAdapter.ViewHolder>(){

    var movies = listOf<MovieInfo>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieInfoAdapter.ViewHolder {
        val item = LayoutInflater.from(parent.context).inflate(R.layout.item_layout,parent,false)
        return ViewHolder(item)
    }

    override fun getItemCount(): Int  = movies.size

    override fun onBindViewHolder(holder: MovieInfoAdapter.ViewHolder, position: Int) = holder.bind(movies[position])

    inner class ViewHolder(val item: View):RecyclerView.ViewHolder(item){
        lateinit var itemListMovieName :TextView
        lateinit var itemListPoster:ImageView
        fun bind(movieInfo: MovieInfo) = with(item){

            itemListMovieName = findViewById(R.id.tv_item_list_movie_name)
            itemListPoster = findViewById(R.id.iv_item_list_poster)

            itemListMovieName.text = movieInfo.Title

            Picasso.get()
                .load(movieInfo.Poster)
                .into(itemListPoster)

            this.setOnClickListener {
                clickListener
            }
        }
    }

    public fun setData(movieInfoList:List<MovieInfo>){
        movies = movieInfoList
        notifyDataSetChanged()
    }
}