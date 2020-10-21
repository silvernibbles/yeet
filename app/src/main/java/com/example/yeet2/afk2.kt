package com.example.yeet2

import android.app.Activity
import android.app.Application
import android.app.Application.ActivityLifecycleCallbacks
import android.hardware.SensorManager
import android.os.Bundle
import android.os.CountDownTimer
import android.os.SystemClock
import android.view.View
import android.widget.Chronometer
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.util.*


class afk2 : AppCompatActivity(){
    var startDate: Date = Calendar.getInstance().getTime()
    var endDate: Date = Calendar.getInstance().getTime()
    var score: Int = 0
    var is_running: Boolean = true


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_afk2)

        score = 0
        startDate = Calendar.getInstance().getTime()
        var meter = findViewById<Chronometer>(R.id.c_meter)
        meter.start()

    }

    override fun onPause() {
        super.onPause()
        if (is_running) {
            endDate = Calendar.getInstance().getTime()
            findDifference()
            val score_txt = findViewById(R.id.score) as TextView
            score_txt.text = "$score"
            var meter = findViewById<Chronometer>(R.id.c_meter)

            meter.stop()

            // If we calculate the score, we don't want the user to get a new
            // score until they call retry
            is_running=false
        }
    }

    override fun onResume() {
        super.onResume()
        if (is_running) {
            score = 0
            var meter = findViewById<Chronometer>(R.id.c_meter)
            meter.setBase(SystemClock.elapsedRealtime());

            startDate = Calendar.getInstance().getTime()

            meter.start()


        }

    }

    fun findDifference() {
        var different = endDate.time - startDate.time
        println("startDate : $startDate")
        println("endDate : $endDate")
        println("different : $different")
        score = different.toInt()

    }
    fun retry_afk(view: View) {
        is_running = true
        onResume()
    }

}