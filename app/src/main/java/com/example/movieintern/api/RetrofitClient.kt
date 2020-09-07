package com.example.movieintern.api


import android.content.Context
import android.content.SharedPreferences
import com.example.movieintern.api.Api
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException

object RetrofitClient {

    private const val BASE_URL = "https://api.themoviedb.org/3/"

    val instance: Api by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        retrofit.create(Api::class.java)
    }



    }



