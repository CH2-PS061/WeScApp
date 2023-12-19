package id.wildexplorerscompanion.ui.profile

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Button
import android.widget.EditText
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
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

        profileBinding.btnDeleteAccount.setOnClickListener {
            showCostumeDialog()
        }

        profileViewModel.getSession().observe(this){
            profileBinding.nameUser.text = it.name
        }
    }

    private fun showCostumeDialog(){
        val builder = AlertDialog.Builder(this)
        val costumeAlertDialog = LayoutInflater.from(this).inflate(R.layout.dialog_delete_password,null)
        builder.setView(costumeAlertDialog)
        val dialog = builder.create()
        val btnCancle = costumeAlertDialog.findViewById<Button>(R.id.btn_delete_no)
        val btnYes = costumeAlertDialog.findViewById<Button>(R.id.btn_delete_ya)
        val edtPassword = costumeAlertDialog.findViewById<EditText>(R.id.edt_con_password)
        btnYes.setOnClickListener {
            profileViewModel.getSession().observe(this){
                val getToken = it.token
                val getEmail = it.email
                val getPassword = edtPassword.text.toString()
                profileViewModel.getDelete(getToken,getEmail,getPassword)
            }
            startActivity(Intent(this@ProfileActivity, LoginActivity::class.java))
            profileViewModel.logout()
            dialog.dismiss()
            finish()
        }
        btnCancle.setOnClickListener {
            dialog.dismiss()
        }
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.show()
    }

}