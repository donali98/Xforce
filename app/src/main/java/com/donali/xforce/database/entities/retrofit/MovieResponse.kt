package com.donali.xforce.database.entities.retrofit

import com.donali.xforce.database.entities.MovieInfo
import com.squareup.moshi.Json

class MovieResponse(
    @field:Json(name = "Search")
    val results:List<MovieInfo>
)