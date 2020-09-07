package com.example.movieintern

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.Toast
import androidx.recyclerview.widget.*
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.ParsedRequestListener
import com.example.movieintern.Adapter.MovieAdapter
import com.example.movieintern.Models.MoviesList
import com.example.movieintern.Models.Result
import com.example.movieintern.api.RetrofitClient
import com.ezyschooling.api.models.DefaultResponse
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private val movieList: MutableList<Result> = mutableListOf()
    private lateinit var adapter: MovieAdapter
lateinit var recyclerMovieList: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        progressBar.visibility= View.VISIBLE
        retryButton1.visibility=View.GONE
        recyclerMovieList=findViewById(R.id.recyclerview1)
        getList()

    }
    fun getList()
    {
        adapter=MovieAdapter(movieList)
        RetrofitClient.instance.movieList()
            .enqueue(object: Callback<MoviesList> {
                override fun onFailure(call: Call<MoviesList>, t: Throwable) {
                    Toast.makeText(applicationContext, "" + t.message, Toast.LENGTH_SHORT).show()
                    Log.i("vdgdfgdf",t.message)
                    retryButton1.visibility=View.VISIBLE
                    retryButton1.setOnClickListener {
                        getList()
                    }
                }

                override fun onResponse(call: Call<MoviesList>, response: Response<MoviesList>) {

                    movieList.addAll(response.body()?.results!!)
                    progressBar.visibility=View.GONE
                    retryButton1.visibility=View.GONE
                    adapter.notifyDataSetChanged()
                }




                //    Toast.makeText(applicationContext,"" + response.body()?.key+response.code(), Toast.LENGTH_SHORT).show()


            })




        recyclerMovieList.layoutManager = GridLayoutManager(this, 2)
        recyclerMovieList.addItemDecoration(DividerItemDecoration(this, OrientationHelper.VERTICAL))
        recyclerMovieList.adapter = adapter
    }
}
