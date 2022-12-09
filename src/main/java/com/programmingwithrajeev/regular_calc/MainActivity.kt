package com.programmingwithrajeev.regular_calc

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import java.lang.ArithmeticException

class MainActivity : AppCompatActivity() {
    private var tvInput: TextView? = null
    private var isDotAdded: Boolean? = false
    private var isLastDigit: Boolean? = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvInput = findViewById(R.id.tvInput)

    }

    fun onDigit(view: View) {
        tvInput?.append((view as Button).text)
        isLastDigit = true

    }

    fun onOperation(view: View) {
        tvInput?.text?.let {
            if(isLastDigit == true && !isOperatorAdded((it.toString()))){
                tvInput?.append((view as Button).text)
            }
        }
    }

    fun onEqual(view: View) {
        var tvValue = tvInput?.text?.toString()
        val prefix = ""

        try {
            if (tvValue != null) {
                tvValue.startsWith("-")
            }
            if (tvValue != null) {
                tvValue = tvValue.substring(1)
            }


            var result = 0
            if (tvValue != null) {
                if(tvValue.contains("-")) {
                    val splitValue = tvValue?.split("-")
                    val one = splitValue!![0]
                    val two = splitValue!![1]
                    tvInput?.text= (one.toDouble() - two.toDouble()).toString()
                }
            }
            if (tvValue != null) {
                if(tvValue.contains("+")) {
                    val splitValue = tvValue?.split("+")
                    val one = splitValue!![0]
                    val two = splitValue!![1]
                    tvInput?.text= (one.toDouble() + two.toDouble()).toString()
                }
            }
            if (tvValue != null) {
                if(tvValue.contains("*")){
                    val splitValue = tvValue?.split("*")
                    val one = splitValue!![0]
                    val two = splitValue!![1]
                    tvInput?.text= (one.toDouble() * two.toDouble()).toString()
                }
            }
            if (tvValue != null) {
                if(tvValue.contains("/")) {
                    val splitValue = tvValue?.split("/")
                    val one = splitValue!![0]
                    val two = splitValue[1]
                    tvInput?.text= (one.toDouble() / two.toDouble()).toString()
                }
            }

        }catch (e: ArithmeticException){
            print(e.toString())
        }
    }
    private fun isOperatorAdded(input : String): Boolean {

        return if (input.startsWith("-")) {
            false
        } else {
            (input.contains("-")
                    || input.contains("+")
                    || input.contains("*") ||
                    input.contains("/"))
        }
    }

    fun onClear(view: View) {
        tvInput?.text = ""
        isDotAdded = false
        isLastDigit = false
    }

    fun onDot(view: View){
        if((isLastDigit == true) && !isDotAdded!!){
            tvInput?.append((view as Button).text)
            isDotAdded = true
            isLastDigit = false
        }
    }
}
