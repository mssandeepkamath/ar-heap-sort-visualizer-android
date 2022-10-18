package com.example.sceneformar

import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.os.Handler
import android.view.KeyEvent
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import com.royrodriguez.transitionbutton.TransitionButton
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class GetStartedActivity : AppCompatActivity() {
    lateinit var myTextView: TextView
    lateinit var webView:WebView
    lateinit var transitionButton: TransitionButton
    lateinit var progressBar: ProgressBar



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.statusBarColor = ContextCompat.getColor(this, R.color.black)
        setContentView(R.layout.activity_get_started)
        myTextView = findViewById(R.id.txt_type_writter_2)
        myTextView.typeWrite(this,"Before you get started..",50L)
        webView = findViewById<View>(R.id.webView) as WebView
        progressBar = findViewById(R.id.progressBar)
        webView.webViewClient = WebViewClient()
        webView.loadUrl("https://www.educative.io/blog/data-structure-heaps-guide")
        webView.settings.javaScriptEnabled = true
        transitionButton = findViewById(R.id.transition_button)

        transitionButton.setOnClickListener {

            transitionButton.startAnimation()


            val handler = Handler()
            handler.postDelayed(Runnable {
                val isSuccessful = true

                // Choose a stop animation if your call was succesful or not
                if (isSuccessful) {
                    transitionButton.stopAnimation(TransitionButton.StopAnimationStyle.EXPAND,
                        TransitionButton.OnAnimationStopEndListener {
                            val intent = Intent(this, MainActivity::class.java)
                            intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
                            startActivity(intent)
                            finish()
                        })
                } else {
                    transitionButton.stopAnimation(TransitionButton.StopAnimationStyle.SHAKE, null)
                }
            }, 2000)

        }
    }


    fun TextView.typeWrite(lifecycleOwner: LifecycleOwner, text: String, intervalMs: Long) {
        lifecycleOwner.lifecycleScope.launch {
            repeat(text.length) {
                delay(intervalMs)
                this@typeWrite.text = text.take(it + 1)
            }
        }
    }

    inner class WebViewClient : android.webkit.WebViewClient() {

        // Load the URL
        override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
            view.loadUrl(url)
            return false
        }

        // ProgressBar will disappear once page is loaded
        override fun onPageFinished(view: WebView, url: String) {
            super.onPageFinished(view, url)
            progressBar.visibility = View.GONE
        }
    }

}