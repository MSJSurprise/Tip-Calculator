package com.example.tipcalculator

import android.animation.ArgbEvaluator
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.EditText
import android.widget.SeekBar
import android.widget.TextView
import androidx.core.content.ContextCompat
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

        seekBarTip.progress = INITIAL_NUMBER
        tipPercentage.text = "$INITIAL_NUMBER%"
        tipDescription(INITIAL_NUMBER)


        seekBarTip.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
                tipPercentage.text = "$p1%"
                computeTipAndTotal()
                Log.i(TAG, "onProgressChange $p1")
                tipDescription(p1)
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {}
            override fun onStopTrackingTouch(p0: SeekBar?) {}
        })

        editBaseText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun afterTextChanged(p0: Editable?) {
                Log.i(TAG, "afterTextChange$p0")
                computeTipAndTotal()
            }
        })

    }

    private fun computeTipAndTotal() {
        // Get the value of the base tip
        val seekBarTip: SeekBar = findViewById(R.id.seek_bar_tip_percentage)
        val editBaseText: EditText = findViewById(R.id.edit_text_base)
        val tipAmount: TextView = findViewById(R.id.text_tip_result)
        val total: TextView = findViewById(R.id.text_total_result)
        if (editBaseText.text.isEmpty()) {
            tipAmount.text = ""
            total.text = ""
            return
        }
        val baseAmount = editBaseText.text.toString().toDouble()
        val tip = seekBarTip.progress
        val tipTotal = baseAmount * tip / 100
        tipAmount.text = "%.2f".format(tipTotal)

        val allTotal = baseAmount + tipTotal
        total.text = "%.2f".format(allTotal)

    }

    private fun tipDescription(tipProgress: Int) {
        val tipDesc: TextView = findViewById(R.id.text_tip_description)
        val seekBarTip: SeekBar = findViewById(R.id.seek_bar_tip_percentage)
        val updateTipDescription: String = when (tipProgress) {
            in 0..9 -> "Poor"
            in 10..14 -> "Acceptable"
            in 15..19 -> "Good"
            in 20..24 -> "Great"
            else -> "Amazing"
        }
        tipDesc.text = updateTipDescription
        val color = ArgbEvaluator().evaluate(tipProgress.toFloat() / seekBarTip.max,
            ContextCompat.getColor(this, R.color.colorWorstTip),
            ContextCompat.getColor(this, R.color.colorBestTip)
        ) as Int
        tipDesc.setTextColor(color)
    }

}