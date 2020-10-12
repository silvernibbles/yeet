package com.example.yeet2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun do_yeet(view: View){
        val tv1 = findViewById(R.id.mytext) as TextView
        tv1.text = "Hello"
        val intent = Intent(this, yeet_game::class.java).apply {
            //putExtra(EXTRA_MESSAGE, message)
        }
        startActivity(intent)

    }
}