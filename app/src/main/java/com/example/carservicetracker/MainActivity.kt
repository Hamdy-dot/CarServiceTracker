package com.example.carservicetracker

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var serviceTypeInput: EditText
    private lateinit var dateInput: EditText
    private lateinit var notesInput: EditText
    private lateinit var saveButton: Button
    private lateinit var loadButton: Button
    private lateinit var displayText: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main) // Load the layout from XML

        // Link Kotlin variables with UI elements
        serviceTypeInput = findViewById(R.id.serviceTypeInput)
        dateInput = findViewById(R.id.dateInput)
        notesInput = findViewById(R.id.notesInput)
        saveButton = findViewById(R.id.saveButton)
        loadButton = findViewById(R.id.loadButton)
        displayText = findViewById(R.id.displayText)

        // Save record to internal storage
        saveButton.setOnClickListener {
            val type = serviceTypeInput.text.toString()
            val date = dateInput.text.toString()
            val notes = notesInput.text.toString()

            val record = ServiceRecord(type, date, notes)
            StorageUtil.saveRecord(this, record)
            Toast.makeText(this, "Record Saved", Toast.LENGTH_SHORT).show()
        }

        // Load and show all saved records
        loadButton.setOnClickListener {
            val records = StorageUtil.loadRecords(this)
            val output = StringBuilder()
            for (record in records) {
                output.append(record.toString()).append("\n\n")
            }
            displayText.text = output.toString()
        }
    }
}
