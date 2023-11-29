package id.wildexplorerscompanion.ui.splashscreen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import id.wildexplorerscompanion.R
import id.wildexplorerscompanion.databinding.ActivitySplashScreenBinding
import id.wildexplorerscompanion.ui.home.MainActivity
import id.wildexplorerscompanion.ui.login.LoginActivity

class SplashScreenActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySplashScreenBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Handler().postDelayed({
            startActivity(Intent(this,LoginActivity::class.java))

            finish()
        }, SPLASH_DELAY)


    }

    companion object {
        private const val SPLASH_DELAY = 3000L
    }
}