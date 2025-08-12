package com.example.healthapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

/**
 * The main screen of the app, displayed after the user logs in.
 *
 * This activity serves as a dashboard, providing navigation to the app's main features.
 */
class MainActivity : AppCompatActivity() {
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
    }
}
