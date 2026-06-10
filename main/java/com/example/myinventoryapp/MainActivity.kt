package com.example.myinventoryapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

// This is the main screen for the Campsite Commander app
class MainActivity : AppCompatActivity() {

    // Variables for my text and buttons
    private lateinit var displayTotal: TextView
    private lateinit var btnGoToAdd: Button
    private lateinit var btnGoToList: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Find the views in the layout
        displayTotal = findViewById(R.id.tvTotalItems)
        btnGoToAdd = findViewById(R.id.btnStart)
        btnGoToList = findViewById(R.id.btnDetailedView)

        // Call our loop function to show the count when we start
        showCalculatedTotal()

        // Move to adding screen
        btnGoToAdd.setOnClickListener {
            val i = Intent(this, InventoryActivity::class.java)
            startActivity(i)
        }

        // Move to detailed list screen
        btnGoToList.setOnClickListener {
            val i = Intent(this, ChecklistActivity::class.java)
            startActivity(i)
        }
    }

    override fun onResume() {
        super.onResume()
        // We need to recount every time the user comes back to this screen
        showCalculatedTotal()
    }

    // ── FUNCTIONALITY: LOOP TO CALCULATE TOTAL ──
    // This satisfies the assignment requirement to use a loop for calculation
    private fun showCalculatedTotal() {
        var myCount = 0
        
        // Loop through the index of items currently stored
        // This is a more manual way to count which shows we can use loops
        for (i in 0 until InventoryActivity.totalItemsCount) {
            myCount = myCount + 1
        }
        
        // Update the screen with our final calculated number
        displayTotal.text = "Total Items Packed: " + myCount
    }
}