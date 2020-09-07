package com.example.movieintern

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.android.youtube.player.YouTubePlayer
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayerSupportFragment
class YouTubeActivity : AppCompatActivity(), YouTubePlayer.OnInitializedListener {
    lateinit var id: String
    private val RECOVERY_DIALOG_REQUEST = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.youtube_layout)
        val intent: Intent
        intent = getIntent()
        val extras = intent.extras
        if (extras != null) {
            id = extras.getString("key").toString()
        }
        val youTubePlayerFragment =
            supportFragmentManager.findFragmentById(R.id.official_player_view) as YouTubePlayerSupportFragment?
        youTubePlayerFragment?.initialize("AIzaSyC6DlFcocAY1M8GUcf8r6hEKRPqWkx52ds", this)

    }


        override fun onInitializationSuccess(
            provider: YouTubePlayer.Provider, youTubePlayer: YouTubePlayer, wasRestored: Boolean
        ) {
            if (!wasRestored) {
                youTubePlayer.cueVideo(id)
            }
        }

        override fun onInitializationFailure(
            provider: YouTubePlayer.Provider,
            youTubeInitializationResult: YouTubeInitializationResult
        ) {
            if (youTubeInitializationResult.isUserRecoverableError) {
                youTubeInitializationResult.getErrorDialog(this, RECOVERY_DIALOG_REQUEST).show()
            } else {
                val errorMessage = String.format(
                    "There was an error initializing the YouTubePlayer (%1\$s)",
                    youTubeInitializationResult.toString()
                )
                Toast.makeText(this, errorMessage, Toast.LENGTH_LONG).show()
            }
        }
    }

