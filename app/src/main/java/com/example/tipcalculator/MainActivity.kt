package com.example.tipcalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.EditText
import android.widget.SeekBar
import android.widget.TextView
import java.nio.channels.SelectionKey

private const val TAG = "MainActivity"
private const val INITIAL_NUMBER = 15

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val seekBarTip: SeekBar = findViewById(R.id.seek_bar_tip_percentage)
        val tipPercentage: TextView = findViewById(R.id.text_tip_percentage)
        val editBaseText: TextView = findViewById(R.id.edit_text_base)
//        val tipPercentage: TextView = findViewById(R.id.text_tip_percentage)

        seekBarTip.progress = INITIAL_NUMBER
        tipPercentage.text ="$INITIAL_NUMBER%"

        seekBarTip.setOnSeekBarChangeListener(object: SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
                tipPercentage.text = "$p1%"
                Log.i(TAG, "onProgressChange $p1")
            }
            override fun onStartTrackingTouch(p0: SeekBar?) {}
            override fun onStopTrackingTouch(p0: SeekBar?) {}
        })

    }

//    private fun computeTipAndTotal() {
//
//    }
}