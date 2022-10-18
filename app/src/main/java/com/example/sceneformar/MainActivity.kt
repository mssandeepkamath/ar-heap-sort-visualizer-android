package com.example.sceneformar



import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class MainActivity : AppCompatActivity() {

    lateinit var myTextView: TextView
    lateinit var cardView1:CardView
    lateinit var cardView2:CardView
    lateinit var cardView3 : CardView
    lateinit var cardView4:CardView




    override fun onCreate(savedInstanceState: Bundle?) {
        //set status bar color to white
        window.statusBarColor = ContextCompat.getColor(this, R.color.black)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        myTextView = findViewById(R.id.txt_type_writter)
        myTextView.typeWrite(this,"Welcome to HeapSort Visualizer, What would you like to try?",70L)
        cardView1=findViewById(R.id.cardView_1)
        cardView2=findViewById(R.id.cardView_2)
        cardView3=findViewById(R.id.cardView_3)
        cardView1.setOnClickListener {
            val intent= Intent(this,AnimationVideoActivity::class.java)
            startActivity(intent)
        }

        cardView2.setOnClickListener {
            val intent=Intent(this,NewArActivity::class.java)
            startActivity(intent)
        }

        cardView3.setOnClickListener {
            val intent=Intent(this,PDFActivity::class.java)
            startActivity(intent)
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



}