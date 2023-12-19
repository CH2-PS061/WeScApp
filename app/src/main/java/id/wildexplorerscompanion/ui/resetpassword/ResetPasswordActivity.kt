package id.wildexplorerscompanion.ui.resetpassword

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.viewModels
import id.wildexplorerscompanion.R
import id.wildexplorerscompanion.databinding.ActivityResetPasswordBinding
import id.wildexplorerscompanion.ui.ViewModelFactory

class ResetPasswordActivity : AppCompatActivity() {
    private lateinit var resetBinding: ActivityResetPasswordBinding
    private val resetViewModel by viewModels<ResetViewModel> {
        ViewModelFactory.getInstance(this)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        resetBinding = ActivityResetPasswordBinding.inflate(layoutInflater)
        setContentView(resetBinding.root)
        buttonClick()

        //Untuk Top NavBar
        supportActionBar?.title = getString(R.string.change_password)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        resetViewModel.resetPassword.observe(this){
            if (it.success){
            showToast("Password Berhasil Di Ganti")
            } else showToast(it.message)
        }
    }

    private fun buttonClick(){
        resetBinding.btnChangePassword.setOnClickListener {
            resetViewModel.getSession().observe(this){
                val getToken = it.token
                val email = resetBinding.edtTextEmail.text.toString()
                val password = resetBinding.edtTextPassword.text.toString()
                val newPassword = resetBinding.edtTextNewpassword.text.toString()
                resetViewModel.getResetPassword(getToken,email, password, newPassword)
            }
        }
    }
    private fun showToast(message: String){
        Toast.makeText(this,message, Toast.LENGTH_LONG).show()
    }

}