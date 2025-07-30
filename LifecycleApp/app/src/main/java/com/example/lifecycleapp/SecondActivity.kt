package com.example.lifecycleapp

import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_second)
        Log.d("SecondActivity", "onCreate() called")

        // Set up the button to return to MainActivity
        val buttonToMain = findViewById<Button>(R.id.button_to_main)
        buttonToMain.setOnClickListener {
            finish()
        }
    }

    override fun onStart() {
        super.onStart()
        Log.d("SecondActivity", "onStart() called")
    }

    override fun onResume() {
        super.onResume()
        Log.d("SecondActivity", "onResume() called")
    }

    override fun onPause() {
        super.onPause()
        Log.d("SecondActivity", "onPause() called")
    }

    override fun onStop() {
        super.onStop()
        Log.d("SecondActivity", "onStop() called")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("SecondActivity", "onDestroy() called")
    }
}