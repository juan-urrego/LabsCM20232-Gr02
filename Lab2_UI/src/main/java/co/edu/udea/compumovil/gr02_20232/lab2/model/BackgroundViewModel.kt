package co.edu.udea.compumovil.gr02_20232.lab2.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.work.OneTimeWorkRequest
import androidx.work.WorkManager
import androidx.work.WorkRequest
import androidx.work.Worker
import androidx.work.WorkerParameters

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
            // Simula una espera de 10 segundos
            Thread.sleep(10000)
        } catch (e: InterruptedException) {
            return Result.failure()
        }

        return Result.success()
    }

}