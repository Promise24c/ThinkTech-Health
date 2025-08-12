package com.example.healthapp

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.healthapp.data.AppDatabase
import com.example.healthapp.model.Medication
import com.example.healthapp.receiver.NotificationReceiver
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.util.*

/**
 * An activity for adding a new medication to the user's list.
 */
class AddMedicationActivity : AppCompatActivity() {
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
        setContentView(R.layout.activity_add_medication)

        userId = intent.getIntExtra("USER_ID", -1)

        val nameEditText = findViewById<EditText>(R.id.medication_name)
        val dosageEditText = findViewById<EditText>(R.id.dosage)
        val frequencyEditText = findViewById<EditText>(R.id.frequency)
        val timeEditText = findViewById<EditText>(R.id.time)
        val stockEditText = findViewById<EditText>(R.id.stock)
        val lowStockThresholdEditText = findViewById<EditText>(R.id.low_stock_threshold)
        val saveButton = findViewById<Button>(R.id.save_medication_button)

        saveButton.setOnClickListener {
            val name = nameEditText.text.toString()
            val dosage = dosageEditText.text.toString()
            val frequency = frequencyEditText.text.toString()
            val time = timeEditText.text.toString()
            val stock = stockEditText.text.toString().toIntOrNull()
            val lowStockThreshold = lowStockThresholdEditText.text.toString().toIntOrNull()

            if (name.isEmpty() || dosage.isEmpty() || frequency.isEmpty() || time.isEmpty() || stock == null || lowStockThreshold == null) {
                Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val medication = Medication(
                userId = userId,
                name = name,
                dosage = dosage,
                frequency = frequency,
                time = time,
                stock = stock,
                lowStockThreshold = lowStockThreshold
            )

            GlobalScope.launch(Dispatchers.IO) {
                val db = AppDatabase.getDatabase(applicationContext)
                val medicationId = db.medicationDao().insert(medication).toInt()
                scheduleNotifications(medication.copy(id = medicationId))
                finish()
            }
        }
    }

    private fun scheduleNotifications(medication: Medication) {
        val alarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager

        val times = medication.time.split(",").map { it.trim() }
        for ((index, time) in times.withIndex()) {
            val intent = Intent(this, NotificationReceiver::class.java).apply {
                putExtra("medication_name", medication.name)
            }

            val timeParts = time.split(":")
            if (timeParts.size != 2) continue

            val calendar = Calendar.getInstance().apply {
                set(Calendar.HOUR_OF_DAY, timeParts[0].toInt())
                set(Calendar.MINUTE, timeParts[1].toInt())
                set(Calendar.SECOND, 0)
            }

            // Use a unique request code for each pending intent
            val requestCode = medication.id * 100 + index

            val pendingIntent = PendingIntent.getBroadcast(
                this,
                requestCode,
                intent,
                PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
            )

            alarmManager.setInexactRepeating(
                AlarmManager.RTC_WAKEUP,
                calendar.timeInMillis,
                AlarmManager.INTERVAL_DAY,
                pendingIntent
            )
        }
    }
}
