package id.wildexplorerscompanion.ui.profile

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import id.wildexplorerscompanion.R
import id.wildexplorerscompanion.databinding.ActivityProfileBinding

class ProfileActivity : AppCompatActivity() {
    private lateinit var profileBinding: ActivityProfileBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        profileBinding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(profileBinding.root)

        //Untuk Top NavBar
        supportActionBar?.title = getString(R.string.profile)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)


    }
}