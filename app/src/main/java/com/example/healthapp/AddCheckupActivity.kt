package com.example.healthapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.healthapp.data.AppDatabase
import com.example.healthapp.model.Checkup
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

/**
 * An activity for adding a new checkup or appointment to the user's schedule.
 */
class AddCheckupActivity : AppCompatActivity() {
    private var userId: Int = -1

    /**
     * Called when the activity is first created.
     *
     * This is where you should do all of your normal static set up:
     * create views, bind data to lists, etc.
     *
     * @param savedInstanceState If the activity is being re-initialized after
     *     previously being shut down then this Bundle contains the data it most
     *     recently supplied in onSaveInstanceState(Bundle).
     *     Note: Otherwise it is null.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_checkup)

        userId = intent.getIntExtra("USER_ID", -1)

        val doctorNameEditText = findViewById<EditText>(R.id.doctor_name)
        val locationEditText = findViewById<EditText>(R.id.location)
        val dateEditText = findViewById<EditText>(R.id.date)
        val notesEditText = findViewById<EditText>(R.id.notes)
        val saveButton = findViewById<Button>(R.id.save_checkup_button)

        saveButton.setOnClickListener {
            val doctorName = doctorNameEditText.text.toString()
            val location = locationEditText.text.toString()
            val dateStr = dateEditText.text.toString()
            val notes = notesEditText.text.toString()

            if (doctorName.isEmpty() || location.isEmpty() || dateStr.isEmpty()) {
                Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val date = try {
                SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).parse(dateStr)?.time ?: 0L
            } catch (e: Exception) {
                Toast.makeText(this, "Invalid date format. Please use YYYY-MM-DD", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val checkup = Checkup(
                userId = userId,
                date = date,
                doctorName = doctorName,
                location = location,
                notes = notes
            )

            GlobalScope.launch(Dispatchers.IO) {
                val db = AppDatabase.getDatabase(applicationContext)
                db.checkupDao().insert(checkup)
                finish()
            }
        }
    }
}
