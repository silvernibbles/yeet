package com.example.yeet2

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlin.math.pow

class yeet_game : AppCompatActivity(), SensorEventListener {
    private lateinit  var sensorManager: SensorManager
    private var sensor:Sensor?=null
    var ax:Float?=0.0f
    var ay:Float?=0.0f
    var az:Float?=0.0f

    var totalx:Float=0.0f
    var totaly:Float=0.0f
    var totalz:Float=0.0f

    var score:Int=0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_yeet_game)
        sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_LINEAR_ACCELERATION)

        onResume()

    }

    override fun onResume() {
        super.onResume()
        sensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_NORMAL)
    }

    override fun onPause() {
        super.onPause()
        sensorManager.unregisterListener(this)
    }

    override fun onAccuracyChanged(sensor: Sensor, accuracy: Int) {
        // Do something here if sensor accuracy changes.
    }

    override fun onSensorChanged(event: SensorEvent) {
        // The light sensor returns a single value.
        // Many sensors return 3 values, one for each axis.
        ax = Math.abs(event.values[0])
        ay = Math.abs(event.values[1])
        az = Math.abs(event.values[2])

        totalx += Math.abs(ax as Float)
        totaly += Math.abs(ay as Float)
        totalz += Math.abs(az as Float)


        if((ax as Float+ay as Float+az as Float) < 0.1 && totalx + totaly + totalz > 1) {
            onPause()
            val tv1 = findViewById(R.id.debug) as TextView
            tv1.text = "$totalx, $totaly, $totalz"
            score = Math.sqrt((totalx.pow(2) + totaly.pow(2)+ totalz.pow(2)).toDouble()).toInt()
            val score_txt = findViewById(R.id.score) as TextView
            score_txt.text = "$score"
        }
    }

    fun retry(view: View){
        ax =0.0f
        ay=0.0f
        az=0.0f
        totalx = 0.0f
        totaly = 0.0f
        totalz = 0.0f

        onResume()

    }
}