package com.example.movieintern.api


import com.example.movieintern.Models.MoviesList
import com.example.movieintern.Models.Result
import retrofit2.Call
import retrofit2.http.*

interface Api {



    @GET("movie/popular?api_key=5dd3068df7cdb05cdd8b5ae379ed55f0&language=en-US&page=1")
    fun movieList():Call<MoviesList>

}