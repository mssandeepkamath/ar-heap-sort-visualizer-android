package com.example.sceneformar

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.YouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.options.IFramePlayerOptions
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView
import com.pierfrancescosoffritti.androidyoutubeplayer.core.ui.DefaultPlayerUiController


class AnimationVideoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_animation_video)
        window.statusBarColor = ContextCompat.getColor(this, R.color.black)
        val thirdPartyYouTubePlayerView =
            findViewById<YouTubePlayerView>(R.id.third_party_player_view)

        thirdPartyYouTubePlayerView.enableAutomaticInitialization =
            false // We set it to false because we init it manually

        val listener: YouTubePlayerListener = object : AbstractYouTubePlayerListener() {
            override fun onReady(youTubePlayer: YouTubePlayer) {
                // We're using pre-made custom ui
                val defaultPlayerUiController =
                    DefaultPlayerUiController(thirdPartyYouTubePlayerView, youTubePlayer)
                defaultPlayerUiController.showFullscreenButton(true)

                // When the video is in full-screen, cover the entire screen
                defaultPlayerUiController.setFullScreenButtonClickListener {
                    if (thirdPartyYouTubePlayerView.isFullScreen()) {
                        thirdPartyYouTubePlayerView.exitFullScreen()
                        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_VISIBLE
                        // Show ActionBar
                        if (supportActionBar != null) {
                            supportActionBar!!.show()
                        }
                    } else {
                        thirdPartyYouTubePlayerView.enterFullScreen()
                        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
                        // Hide ActionBar
                        if (supportActionBar != null) {
                            supportActionBar!!.hide()
                        }
                    }
                }


                thirdPartyYouTubePlayerView.setCustomPlayerUi(defaultPlayerUiController.rootView)

                val videoId = "N4a2mH3eKzg"
                youTubePlayer.cueVideo(videoId, 0f)
            }
        }

        // Disable iFrame UI
        val options: IFramePlayerOptions = IFramePlayerOptions.Builder().controls(0).build()
        thirdPartyYouTubePlayerView.initialize(listener, options)

    }

    }
