package com.example.healthapp.receiver

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat
import com.example.healthapp.R

/**
 * A BroadcastReceiver that handles displaying medication reminder notifications.
 */
class NotificationReceiver : BroadcastReceiver() {

    /**
     * This method is called when the BroadcastReceiver is receiving an Intent broadcast.
     *
     * @param context The Context in which the receiver is running.
     * @param intent The Intent being received.
     */
    override fun onReceive(context: Context, intent: Intent) {
        val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        // Create a notification channel for Android 8.0 (Oreo) and above.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel("medication_reminder", "Medication Reminder", NotificationManager.IMPORTANCE_DEFAULT)
            notificationManager.createNotificationChannel(channel)
        }

        // Get the medication name from the intent.
        val medicationName = intent.getStringExtra("medication_name")

        // Build the notification.
        val notification = NotificationCompat.Builder(context, "medication_reminder")
            .setContentTitle("Medication Reminder")
            .setContentText("It's time to take your $medicationName.")
            .setSmallIcon(R.drawable.ic_launcher_foreground) // You'll need to create this drawable
            .build()

        // Show the notification.
        notificationManager.notify(1, notification)
    }
}
