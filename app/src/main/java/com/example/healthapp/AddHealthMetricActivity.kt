package com.example.healthapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.healthapp.data.AppDatabase
import com.example.healthapp.model.HealthMetric
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.util.*

/**
 * An activity for adding a new health metric reading.
 */
class AddHealthMetricActivity : AppCompatActivity() {
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
        setContentView(R.layout.activity_add_health_metric)

        userId = intent.getIntExtra("USER_ID", -1)

        val bloodPressureEditText = findViewById<EditText>(R.id.blood_pressure)
        val bloodSugarEditText = findViewById<EditText>(R.id.blood_sugar)
        val heartRateEditText = findViewById<EditText>(R.id.heart_rate)
        val notesEditText = findViewById<EditText>(R.id.notes)
        val saveButton = findViewById<Button>(R.id.save_health_metric_button)

        saveButton.setOnClickListener {
            val bloodPressure = bloodPressureEditText.text.toString()
            val bloodSugar = bloodSugarEditText.text.toString()
            val heartRate = heartRateEditText.text.toString()
            val notes = notesEditText.text.toString()

            if (bloodPressure.isEmpty() || bloodSugar.isEmpty() || heartRate.isEmpty()) {
                Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val healthMetric = HealthMetric(
                userId = userId,
                date = System.currentTimeMillis(),
                bloodPressure = bloodPressure,
                bloodSugar = bloodSugar,
                heartRate = heartRate,
                notes = notes
            )

            GlobalScope.launch(Dispatchers.IO) {
                val db = AppDatabase.getDatabase(applicationContext)
                db.healthMetricDao().insert(healthMetric)
                finish()
            }
        }
    }
}
