package com.example.healthapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.healthapp.adapter.MedicationAdapter
import com.example.healthapp.data.AppDatabase
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

/**
 * An activity for viewing a list of the user's medications.
 */
class ViewMedicationsActivity : AppCompatActivity() {
    private var userId: Int = -1
    private lateinit var medicationAdapter: MedicationAdapter

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
        setContentView(R.layout.activity_view_medications)

        userId = intent.getIntExtra("USER_ID", -1)

        val recyclerView = findViewById<RecyclerView>(R.id.medications_recycler_view)
        medicationAdapter = MedicationAdapter(emptyList())
        recyclerView.adapter = medicationAdapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        lifecycleScope.launch {
            val db = AppDatabase.getDatabase(applicationContext)
            db.medicationDao().getMedicationsForUser(userId).collect { medications ->
                medicationAdapter.updateData(medications)
            }
        }
    }
}
