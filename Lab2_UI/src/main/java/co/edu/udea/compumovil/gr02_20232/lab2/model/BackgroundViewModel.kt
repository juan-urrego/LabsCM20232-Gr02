package co.edu.udea.compumovil.gr02_20232.lab2.model

import android.app.NotificationChannel
import android.content.Context
import androidx.core.content.ContextCompat.getSystemService
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.work.OneTimeWorkRequest
import androidx.work.WorkManager
import androidx.work.WorkRequest
import androidx.work.Worker
import androidx.work.WorkerParameters
import android.app.NotificationManager
import androidx.core.app.NotificationCompat
import co.edu.udea.compumovil.gr02_20232.lab2.R

class BackgroundViewModel: ViewModel() {
    fun startBackGroundTask() {
        val workRequest: WorkRequest = OneTimeWorkRequest.Builder(MyWorker::class.java).build()
        WorkManager.getInstance().enqueue(workRequest)
    }
}

class MyWorker(
    appContext: android.content.Context,
    workerParams: WorkerParameters
) : Worker(appContext, workerParams) {

    private val resultLiveData = MutableLiveData<String>()

    val result: LiveData<String> = resultLiveData
    override fun doWork(): Result {

        //codigo en background
        try {
            Thread.sleep(10000)
            val notificationManager = applicationContext.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            val channelId = "mi_canal_de_notificacion"
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                val channel = NotificationChannel(channelId, "Canal de encuesta", NotificationManager.IMPORTANCE_DEFAULT)
                notificationManager.createNotificationChannel(channel)
            }

            val notification = NotificationCompat.Builder(applicationContext, channelId)
                .setContentTitle("Encuesta completada")
                .setContentText("¡Gracias por completar la encuesta!")
                .setSmallIcon(R.drawable.ic_logo_dark)
                .setAutoCancel(true)

            val NOTIFICATION_ID = 1

            notificationManager.notify(NOTIFICATION_ID, notification.build())

        } catch (e: InterruptedException) {
            return Result.failure()
        }

        return Result.success()
    }

}