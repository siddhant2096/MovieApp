package com.example.movieintern.Adapter

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.*
import com.example.movieintern.Models.Result
import com.example.movieintern.MovieDetailActivity
import com.example.movieintern.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.layout_movie_item.view.*

class MovieAdapter(private val movielist: MutableList<Result>) : RecyclerView.Adapter<MovieAdapter.MovieHolder>() {
    private lateinit var context: Context

    class MovieHolder(itemView: View):RecyclerView.ViewHolder(itemView) {
        var name: TextView
        var profilephoto: ImageView
        //   var txt_team : TextView
        //  var txt_createdby : TextView

        init {

            name = itemView.movie_name
            profilephoto= itemView.image_movie
            //  txt_team = itemView.txt_team
            // txt_createdby = itemView.txt_createdby
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieHolder {
        context=parent.context
        return MovieHolder(LayoutInflater.from(context).inflate(R.layout.layout_movie_item,parent,false))    }

    override fun getItemCount(): Int {
return movielist.size   }





    override fun onBindViewHolder(holder: MovieHolder, position: Int) {
        holder.name.text=movielist[position].title
        val profile=holder.profilephoto
        var imageurl="https://image.tmdb.org/t/p/w185"+movielist[position].backdropPath
        Log.i("image",imageurl)
        Picasso.get().load(imageurl).into(profile)
    holder.itemView.setOnClickListener {
        val intent = Intent(context, MovieDetailActivity::class.java)
        intent.putExtra("id",movielist[position].id.toString())
        ContextCompat.startActivity(context, intent, Bundle())
    }}


}

