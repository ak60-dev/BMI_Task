package com.example.appfirst

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    private lateinit var waittext: EditText
    private  lateinit var heighttext: EditText
    private lateinit var bmitv: TextView
    private lateinit var button: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        // Handle window insets
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Initialize the views
        waittext = findViewById(R.id.weighteditText)
        heighttext = findViewById(R.id.heighteditText)
        bmitv = findViewById(R.id.bmiResultTextView)

        button = findViewById(R.id.btn)

        // Set up the button click listener to calculate bmi
        button.setOnClickListener {
            val weighttext = waittext.text.toString()
            val hightxt = heighttext.text.toString()

            if (weighttext.isNotEmpty() && hightxt.isNotEmpty()){
                val wat = weighttext.toFloat()
                val high = hightxt.toFloat()
                val bmi = calculateBMI(wat, high)

                bmitv.text = String.format("your bmi is : %.2f",bmi)
                bmitv.append("\n" + getBMICategory(bmi))

            }else {
                Toast.makeText(this, "Weight or Height is missing", Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun getBMICategory(bmi: Float): String {

        return when {
            bmi < 18.5 -> "Underweight"
            bmi < 24.9 -> "Normal weight"
            bmi < 29.9 -> "Overweight"
            else -> "Obese"
        }

    }

    private fun calculateBMI(wat: Float, high: Float): Float {

        return wat/(high * high)

    }
}
