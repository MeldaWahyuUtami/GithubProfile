package id.ac.polbeng.depandienda.githubprofile.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import id.ac.polbeng.depandienda.githubprofile.databinding.ActivitySplashScreenBinding
import id.ac.polbeng.depandienda.githubprofile.helpers.Config

class SplashScreenActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySplashScreenBinding

    companion object {
        private const val TAG = "SplashScreenActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        try {
            binding = ActivitySplashScreenBinding.inflate(layoutInflater)
            setContentView(binding.root)

            Handler(Looper.getMainLooper()).postDelayed({
                try {
                    val intent = Intent(this@SplashScreenActivity, MainActivity::class.java)
                    startActivity(intent)
                    finish()
                } catch (e: Exception) {
                    Log.e(TAG, "Failed to start MainActivity: ${e.message}", e)
                }
            }, Config.SPLASH_SCREEN_DELAY)
        } catch (e: Exception) {
            Log.e(TAG, "Error in SplashScreenActivity onCreate: ${e.message}", e)
            try {
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            } catch (ex: Exception) {
                // ignore
            }
        }
    }
}
