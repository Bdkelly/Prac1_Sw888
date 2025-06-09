package com.example.prac1_sw888

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import android.widget.Toast
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

class MainActivity : AppCompatActivity() {
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val editTextMonth: EditText = findViewById(R.id.editTextMonth)
        val editTextDay: EditText = findViewById(R.id.editTextDay)
        val editTextYear: EditText = findViewById(R.id.editTextYear)

        val buttonProcessDate: Button = findViewById(R.id.buttonProcessDate)
        val textViewMessage: TextView = findViewById(R.id.textViewMessage)

        buttonProcessDate.setOnClickListener {
            val monthInputString = editTextMonth.text.toString()
            val dayInputString = editTextDay.text.toString()
            val yearInputString = editTextYear.text.toString()

            // Combine the inputs for SimpleDateFormat
            val dateString = "$dayInputString/$monthInputString/$yearInputString"

            // Define the date format to parse the input
            val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
            dateFormat.isLenient = false // Get format
            try {
                // Parse the entered date string into a Date object
                val enteredDate: Date = dateFormat.parse(dateString)!!

                // Get the current date
                val currentDate = Date()

                // Calculate the difference in years
                val calendarEntered = Calendar.getInstance().apply { time = enteredDate }
                val calendarCurrent = Calendar.getInstance().apply { time = currentDate }

                var yearsSince = calendarCurrent.get(Calendar.YEAR) - calendarEntered.get(Calendar.YEAR)

                // Adjust yearsSince if the current date hasn't reached the entered date's month/day yet
                if (calendarCurrent.get(Calendar.DAY_OF_YEAR) < calendarEntered.get(Calendar.DAY_OF_YEAR)) {
                    yearsSince--
                }

                Toast.makeText(this, "Years since birthday: $yearsSince", Toast.LENGTH_LONG).show()

                // Format date for display
                textViewMessage.text = "Birthday: ${dateFormat.format(enteredDate)}"
                textViewMessage.setTextColor(resources.getColor(android.R.color.black))

            } catch (e: Exception) {
                // Error handling for invalid date format
                val errorMessage = "Invalid date entered. Please check month, day, and year."
                textViewMessage.text = errorMessage
                textViewMessage.setTextColor(resources.getColor(android.R.color.holo_red_dark))
                Toast.makeText(this, errorMessage, Toast.LENGTH_LONG).show()
            }
        }
    }
}