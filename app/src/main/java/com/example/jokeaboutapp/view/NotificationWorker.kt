package com.example.jokeaboutapp.view

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.work.CoroutineWorker
import com.example.jokeaboutapp.R
import java.lang.Exception

class NotificationWorker(appContext: Context) : CoroutineWorker(appContext) {
    override suspend fun doWork(): Result {
        try {
            val data = inputData
            val disc: String = data.getString("description")!!
            displayNotification("to the stars", disc)
            return Result.success()
        } catch (e: Exception) {
            return Result.failure()
        }
    }

    private fun displayNotification(task: String, desc: String) {
        val manager = applicationContext.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel("id", "id", NotificationManager.IMPORTANCE_DEFAULT)
            manager.createNotificationChannel(channel)
        }

        val notification = NotificationCompat.Builder(applicationContext, "id")
            .setContentTitle(task)
            .setContentText(desc)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setSmallIcon(R.drawable.ic_launcher_background)
            .build()

        manager.notify(1, notification)
    }
}