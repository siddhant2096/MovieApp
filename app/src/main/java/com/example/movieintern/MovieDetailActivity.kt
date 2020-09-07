package com.example.movieintern

import android.content.Intent
import android.graphics.drawable.GradientDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.Toast
import androidx.recyclerview.widget.*
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.ParsedRequestListener
import com.example.movieintern.Adapter.MovieAdapter
import com.example.movieintern.Adapter.TopVideoAdapter
import com.example.movieintern.DetailsModels.Details
import com.example.movieintern.Models.Result
import com.example.movieintern.VideoModels.Videos
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.layout_movie_detail.*

class MovieDetailActivity : AppCompatActivity() {
    private lateinit var id: String
    lateinit var url: String
    private val videoList: MutableList<com.example.movieintern.VideoModels.Result> = mutableListOf()
    private lateinit var adapter: TopVideoAdapter
    lateinit var videoRecyclerView: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_movie_detail)
progessBar2.visibility=View.VISIBLE
        retryButton.visibility=View.GONE
         videoRecyclerView = findViewById<RecyclerView>(R.id.recyclerview_videos)
        val intent: Intent
        intent = getIntent()
        val extras = intent.extras
        if (extras != null) {
            id = extras.getString("id").toString()
            Log.i("id", id)
        }
         url =
            "https://api.themoviedb.org/3/movie/" + id + "?api_key=5dd3068df7cdb05cdd8b5ae379ed55f0&language=en-US"
        Log.i("fdf", url)
    getMovies()

    }
    fun getMovies()
    {
        AndroidNetworking.initialize(this)
        AndroidNetworking.get(url)
            .build().getAsObject(
                Details::class.java,
                object : ParsedRequestListener<Details> {
                    override fun onResponse(response: Details) {
                        progessBar2.visibility=View.GONE
                        retryButton.visibility=View.GONE
                        var titlePhotoUrl = "https://image.tmdb.org/t/p/w185" + response.posterPath
                        Log.i("php", titlePhotoUrl)
                        Picasso.get().load(titlePhotoUrl).into(titlePhoto)
                        var miniPhotoUrl = "https://image.tmdb.org/t/p/w185" + response.backdropPath
                        Picasso.get().load(miniPhotoUrl).into(miniPhoto)
                        detail_movie_title.text = response.title
                        movie_rating.text = response.voteAverage.toString()
                        movie_release_date.text = response.releaseDate
                        movie_overview.text = response.overview

                    }

                    override fun onError(anError: ANError?) {
                        Log.i("error",anError.toString())
                        retryButton.visibility=View.VISIBLE
                        retryButton.setOnClickListener {
                            getMovies()
                        }
                    }

                }
            )
        var videoUrl="https://api.themoviedb.org/3/movie/"+id+"/videos?api_key=5dd3068df7cdb05cdd8b5ae379ed55f0&language=en-US"
        adapter= TopVideoAdapter(videoList)
        AndroidNetworking.initialize(this)
        AndroidNetworking.get(videoUrl)
            .build().getAsObject(
                Videos::class.java,
                object : ParsedRequestListener<Videos> {
                    override fun onResponse(response:Videos) {
                        videoList.addAll(response.results)

                        adapter.notifyDataSetChanged()

                    }

                    override fun onError(anError: ANError?) {
                    }

                }
            )
        videoRecyclerView.layoutManager = LinearLayoutManager(this,LinearLayout.HORIZONTAL,false)
        videoRecyclerView.addItemDecoration(DividerItemDecoration(this, OrientationHelper.HORIZONTAL))
        videoRecyclerView.adapter = adapter
    }
    }
