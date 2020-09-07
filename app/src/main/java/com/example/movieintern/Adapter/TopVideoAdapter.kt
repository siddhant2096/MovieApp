package com.example.movieintern.Adapter

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.movieintern.R
import com.example.movieintern.VideoModels.Result
import com.example.movieintern.YouTubeActivity

import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.top_video_item.view.*


class TopVideoAdapter(private val dataList: MutableList<Result>): RecyclerView.Adapter<TopVideoAdapter.VideoHolder>() {
    private lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VideoHolder {
        context=parent.context
        return VideoHolder(LayoutInflater.from(context).inflate(R.layout.top_video_item,parent,false))
    }

    override fun getItemCount(): Int {
return dataList.size   }

    override fun onBindViewHolder(holder: VideoHolder, position: Int) {
        var videoId=dataList[position].key.toString()
        var imageurl="https://img.youtube.com/vi/"+ videoId +"/mqdefault.jpg"
        Log.i("imageurl",imageurl)
      Picasso.get().load(imageurl).into(holder.videoimage)
        holder.itemView.setOnClickListener {
            holder.itemView.setOnClickListener {
                //  Toast.makeText(context,"hello",Toast.LENGTH_LONG).show()
                val intent = Intent(context, YouTubeActivity::class.java)
                intent.putExtra("key",videoId.toString())

                ContextCompat.startActivity(context, intent, Bundle())


            }

        }
    }
    class VideoHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var videoimage : ImageView

        //   var txt_team : TextView
        //  var txt_createdby : TextView

        init {

            videoimage = itemView.top_video
            //  txt_team = itemView.txt_team
            // txt_createdby = itemView.txt_createdby
        }
    }

}


