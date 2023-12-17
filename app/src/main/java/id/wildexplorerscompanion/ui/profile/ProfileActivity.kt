package id.wildexplorerscompanion.ui.profile

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import id.wildexplorerscompanion.R
import id.wildexplorerscompanion.databinding.ActivityProfileBinding
import id.wildexplorerscompanion.ui.ViewModelFactory
import id.wildexplorerscompanion.ui.login.LoginActivity
import id.wildexplorerscompanion.ui.resetpassword.ResetPasswordActivity

class ProfileActivity : AppCompatActivity() {
    private lateinit var profileBinding: ActivityProfileBinding
    private val profileViewModel by viewModels<ProfileViewModel> {
        ViewModelFactory.getInstance(this)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        profileBinding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(profileBinding.root)

        //Untuk Top NavBar
        supportActionBar?.title = getString(R.string.profile)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        profileBinding.btnChangePassword.setOnClickListener {
            startActivity(Intent(this@ProfileActivity, ResetPasswordActivity::class.java))
        }

        profileBinding.btnLogout.setOnClickListener {
            startActivity(Intent(this@ProfileActivity, LoginActivity::class.java))
            finish()
            profileViewModel.logout()
        }

        profileViewModel.getSession().observe(this){
            profileBinding.nameUser.text = it.name
        }

        // TODO: Buat Delete Akun dan Change Image Profile belum bisa 
    }

}