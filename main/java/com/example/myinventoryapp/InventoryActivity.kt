package com.example.myinventoryapp

import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

// This page handles adding new items to our camping inventory
class InventoryActivity : AppCompatActivity() {

    // ── DATA STORAGE: PARALLEL ARRAYS ──
    // I am using parallel arrays to save the details of each item.
    // Each detail is stored in a separate array at the exact same index.
    companion object {
        // We initialize these arrays to hold 100 items for the assignment
        var gearNames = arrayOfNulls<String>(100)
        var gearCategories = arrayOfNulls<String>(100)
        var gearQuantities = arrayOfNulls<String>(100)
        var gearNotes = arrayOfNulls<String>(100)
        
        // This tracks the total number of items currently stored
        var totalItemsCount = 0 
        var isSetupDone = false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_inventory)

        // Load the sample data from the worksheet (Tent, Marshmallows, Flashlight)
        if (isSetupDone == false) {
            loadAssignmentData()
            isSetupDone = true
        }

        // Connect our code to the XML interface elements
        val nameInput = findViewById<EditText>(R.id.etItemName)
        val qtyInput = findViewById<EditText>(R.id.etQuantity)
        val commentInput = findViewById<EditText>(R.id.etComments)
        val radioGroup = findViewById<RadioGroup>(R.id.rgCategory)
        val saveBtn = findViewById<Button>(R.id.btnAddItem)
        val listBtn = findViewById<Button>(R.id.btnViewChecklist)

        // Functionality for the "Add Item" button
        saveBtn.setOnClickListener {
            val name = nameInput.text.toString().trim()
            val qty = qtyInput.text.toString().trim()
            val note = commentInput.text.toString().trim()
            val checkedId = radioGroup.checkedRadioButtonId

            // ── ERROR HANDLING ──
            // Provide feedback if fields are left empty to help the user
            if (name == "") {
                Toast.makeText(this, "Please enter the gear name", Toast.LENGTH_SHORT).show()
            } else if (qty == "") {
                Toast.makeText(this, "Please enter the quantity", Toast.LENGTH_SHORT).show()
            } else if (checkedId == -1) {
                Toast.makeText(this, "Please select a category", Toast.LENGTH_SHORT).show()
            } else {
                // Save the data into our parallel arrays
                if (totalItemsCount < 100) {
                    val radio = findViewById<RadioButton>(checkedId)
                    val category = radio.text.toString()

                    // Store details at the current shared index
                    gearNames[totalItemsCount] = name
                    gearCategories[totalItemsCount] = category
                    gearQuantities[totalItemsCount] = qty
                    gearNotes[totalItemsCount] = note
                    
                    // Move the counter up for the next new item
                    totalItemsCount = totalItemsCount + 1

                    // Reset form for next entry
                    nameInput.text.clear()
                    qtyInput.text.clear()
                    commentInput.text.clear()
                    radioGroup.clearCheck()
                    
                    Toast.makeText(this, "Success: $name was added!", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this, "Inventory is full!", Toast.LENGTH_SHORT).show()
                }
            }
        }

        // Screen Navigation: Switch to the Detailed Checklist view
        listBtn.setOnClickListener {
            val goToDetail = Intent(this, ChecklistActivity::class.java)
            startActivity(goToDetail)
        }
    }

    // Function to initialize arrays with the specific sample data from the rubric
    private fun loadAssignmentData() {
        // Entry 1: Tent
        gearNames[0] = "Tent"; gearCategories[0] = "Shelter"; gearQuantities[0] = "1"; gearNotes[0] = "4-person waterproof"
        // Entry 2: Marshmallows
        gearNames[1] = "Marshmallows"; gearCategories[1] = "Food"; gearQuantities[1] = "3"; gearNotes[1] = "For S'mores (Mega size)"
        // Entry 3: Flashlight
        gearNames[2] = "Flashlight"; gearCategories[2] = "Safety"; gearQuantities[2] = "2"; gearNotes[2] = "Check batteries (AA)"
        
        totalItemsCount = 3
    }
}