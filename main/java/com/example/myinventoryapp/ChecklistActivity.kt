package com.example.myinventoryapp

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

// This is the Detailed View screen that shows everything we have packed
class ChecklistActivity : AppCompatActivity() {

    private lateinit var listOutput: TextView
    private lateinit var btnGoBack: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_checklist)

        // Find our UI items
        listOutput = findViewById(R.id.tvChecklistDisplay)
        btnGoBack = findViewById(R.id.btnBack)
        
        // Rubric requirement: Clear navigation buttons
        btnGoBack.text = "Back to Base"

        // This function builds the text for our packing list
        showFullInventory()

        // Navigation: Go back to the main screen
        btnGoBack.setOnClickListener {
            // finish() just closes this screen and takes us back
            finish()
        }
    }

    // ── IMPLEMENTATION OF ARRAYS AND LOOPS ──
    // This function creates the detailed view using a loop and our parallel arrays
    private fun showFullInventory() {
        var reportText = ""
        
        // Getting the data from our shared storage in InventoryActivity
        val names = InventoryActivity.gearNames
        val types = InventoryActivity.gearCategories
        val qtys = InventoryActivity.gearQuantities
        val notes = InventoryActivity.gearNotes
        val totalCount = InventoryActivity.totalItemsCount

        // Check if the list is empty first
        if (totalCount == 0) {
            listOutput.text = "You haven't packed anything yet!"
            return
        }

        // ── LOOPS ──
        // I'm using a loop to go through every item and add it to our report string
        for (i in 0 until totalCount) {
            reportText = reportText + "Gear: " + names[i] + "\n"
            reportText = reportText + "Category: " + types[i] + "\n"
            reportText = reportText + "Quantity: " + qtys[i] + "\n"
            reportText = reportText + "Note/Tip: " + notes[i] + "\n"
            reportText = reportText + "----------------------------\n"
        }
        
        // Finally, show the report on the screen
        listOutput.text = reportText
    }
}