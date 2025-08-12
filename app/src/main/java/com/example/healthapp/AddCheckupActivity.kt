package com.example.healthapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

/**
 * An activity for adding a new checkup or appointment to the user's schedule.
 */
class AddCheckupActivity : AppCompatActivity() {
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
    }
}
