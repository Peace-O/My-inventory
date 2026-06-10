package com.example.myinventoryapp

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        // This is the loading screen. It waits for 3 seconds then goes to the main page.
        // We use a Handler to manage the delay (3000ms = 3 seconds).
        Handler(Looper.getMainLooper()).postDelayed({
            // Navigation: Start the main screen after the wait is over
            val goToMain = Intent(this, MainActivity::class.java)
            startActivity(goToMain)
            
            // Close the splash screen so we can't go back to it
            finish()
        }, 3000)
    }
}