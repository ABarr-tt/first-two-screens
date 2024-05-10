package com.example.barrettsbudgettracker

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import com.google.android.material.textfield.TextInputLayout

class TransactionActivity : AppCompatActivity() {

    private lateinit var labelInput: EditText
    private lateinit var amountInput: EditText
    private lateinit var labelLayout: TextInputLayout
    private lateinit var addTransButton: Button // Change to ImageButton
    private lateinit var amountLayout: TextInputLayout
    private lateinit var closeBtn: ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_transaction)

        // Initialize the views
        labelInput = findViewById(R.id.labelInput)
        amountInput = findViewById(R.id.amountInput)
        labelLayout = findViewById(R.id.labelLayout)
        addTransButton = findViewById(R.id.addTransButton) // Change to ImageButton
        amountLayout = findViewById(R.id.amountLayout)
        closeBtn = findViewById(R.id.closeBtn)

        // Add text changed listeners
        labelInput.addTextChangedListener {
            if (it!!.count() > 0)
                labelLayout.error = null
        }

        amountInput.addTextChangedListener {
            if (it!!.count() > 0)
                amountLayout.error = null
        }

        // Set click listener for the add transaction button
        addTransButton.setOnClickListener {
            val label = labelInput.text.toString()
            val amount = amountInput.text.toString().toDoubleOrNull()

            if (label.isEmpty()) {
                labelLayout.error = "Please enter a valid label"
            } else {
                labelLayout.error = null
            }

            if (amount == null) {
                amountLayout.error = "Please enter a valid amount"
            } else {
                amountLayout.error = null
            }
        }

        // Set click listener for the close button
        closeBtn.setOnClickListener {
            finish()
        }
    }
}
