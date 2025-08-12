package com.example.healthapp.receiver

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat
import com.example.healthapp.R

class NotificationReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel("medication_reminder", "Medication Reminder", NotificationManager.IMPORTANCE_DEFAULT)
            notificationManager.createNotificationChannel(channel)
        }

        val medicationName = intent.getStringExtra("medication_name")

        val notification = NotificationCompat.Builder(context, "medication_reminder")
            .setContentTitle("Medication Reminder")
            .setContentText("It's time to take your $medicationName.")
            .setSmallIcon(R.drawable.ic_launcher_foreground) // You'll need to create this drawable
            .build()

        notificationManager.notify(1, notification)
    }
}
