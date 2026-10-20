package id.ac.polbeng.depandienda.githubprofile

import android.app.Application
import android.os.Handler
import android.os.Looper
import android.widget.Toast

class GithubProfileApp : Application() {
    override fun onCreate() {
        super.onCreate()
        val defaultHandler = Thread.getDefaultUncaughtExceptionHandler()
        Thread.setDefaultUncaughtExceptionHandler { thread, throwable ->
            try {
                Handler(Looper.getMainLooper()).post {
                    Toast.makeText(
                        applicationContext,
                        "Crash: ${throwable.localizedMessage}",
                        Toast.LENGTH_LONG
                    ).show()
                }
                Thread.sleep(4000)
            } catch (e: Exception) {
                e.printStackTrace()
            }
            defaultHandler?.uncaughtException(thread, throwable)
        }
    }
}
