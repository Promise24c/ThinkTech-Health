package com.example.healthapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

/**
 * The main screen of the app, displayed after the user logs in.
 *
 * This activity serves as a dashboard, providing navigation to the app's main features.
 */
class MainActivity : AppCompatActivity() {
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
        setContentView(R.layout.activity_main)

        userId = intent.getIntExtra("USER_ID", -1)

        val viewMedicationsButton = findViewById<Button>(R.id.view_medications_button)
        val addMedicationButton = findViewById<Button>(R.id.add_medication_button)
        val viewHealthMetricsButton = findViewById<Button>(R.id.view_health_metrics_button)
        val addHealthMetricButton = findViewById<Button>(R.id.add_health_metric_button)
        val viewCheckupsButton = findViewById<Button>(R.id.view_checkups_button)
        val addCheckupButton = findViewById<Button>(R.id.add_checkup_button)

        viewMedicationsButton.setOnClickListener {
            val intent = Intent(this, ViewMedicationsActivity::class.java)
            intent.putExtra("USER_ID", userId)
            startActivity(intent)
        }

        addMedicationButton.setOnClickListener {
            val intent = Intent(this, AddMedicationActivity::class.java)
            intent.putExtra("USER_ID", userId)
            startActivity(intent)
        }

        viewHealthMetricsButton.setOnClickListener {
            val intent = Intent(this, ViewHealthMetricsActivity::class.java)
            intent.putExtra("USER_ID", userId)
            startActivity(intent)
        }

        addHealthMetricButton.setOnClickListener {
            val intent = Intent(this, AddHealthMetricActivity::class.java)
            intent.putExtra("USER_ID", userId)
            startActivity(intent)
        }

        viewCheckupsButton.setOnClickListener {
            val intent = Intent(this, ViewCheckupsActivity::class.java)
            intent.putExtra("USER_ID", userId)
            startActivity(intent)
        }

        addCheckupButton.setOnClickListener {
            val intent = Intent(this, AddCheckupActivity::class.java)
            intent.putExtra("USER_ID", userId)
            startActivity(intent)
        }
    }
}
