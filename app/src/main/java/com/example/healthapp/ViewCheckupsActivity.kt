package com.example.healthapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.healthapp.adapter.CheckupAdapter
import com.example.healthapp.data.AppDatabase
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

/**
 * An activity for viewing a list of the user's upcoming and past checkups.
 */
class ViewCheckupsActivity : AppCompatActivity() {
    private var userId: Int = -1
    private lateinit var checkupAdapter: CheckupAdapter

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
        setContentView(R.layout.activity_view_checkups)

        userId = intent.getIntExtra("USER_ID", -1)

        val recyclerView = findViewById<RecyclerView>(R.id.checkups_recycler_view)
        checkupAdapter = CheckupAdapter(emptyList())
        recyclerView.adapter = checkupAdapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        lifecycleScope.launch {
            val db = AppDatabase.getDatabase(applicationContext)
            db.checkupDao().getCheckupsForUser(userId).collect { checkups ->
                checkupAdapter.updateData(checkups)
            }
        }
    }
}
