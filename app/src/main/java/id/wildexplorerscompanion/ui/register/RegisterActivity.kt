package id.wildexplorerscompanion.ui.register

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.TextPaint
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import id.wildexplorerscompanion.R
import id.wildexplorerscompanion.databinding.ActivityRegisterBinding
import id.wildexplorerscompanion.ui.ViewModelFactory
import id.wildexplorerscompanion.ui.login.LoginActivity

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding : ActivityRegisterBinding
    private val registerViewModel by viewModels<RegisterViewModel> {
        ViewModelFactory.getInstance(this)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        signInText()
        showCostumeDialog()

        binding.btnRegister.setOnClickListener {
            val name = binding.edtPersonName.text.toString()
            val email = binding.edtTextEmail.text.toString()
            val password = binding.edtTextPassword.text.toString()
            registerViewModel.getRegister(name,email,password)
        }


    }
    private fun signInText(){
        val text ="Sudah Punya Akun? Masuk Disini"
        val spanString = SpannableString(text)

        val clickAbleSpan = object : ClickableSpan(){
            override fun onClick(widget: View) {
                val intent = Intent(this@RegisterActivity, LoginActivity::class.java)
                startActivity(intent)
            }

            override fun updateDrawState(ds: TextPaint) {
                ds.isUnderlineText = false
                ds.color = getColor(R.color.applegreen)

            }
        }
        spanString.setSpan(clickAbleSpan,18,30, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)

        binding.tvRegister.text = spanString
        binding.tvRegister.highlightColor = Color.TRANSPARENT
        binding.tvRegister.movementMethod = LinkMovementMethod.getInstance()
    }

    private fun showCostumeDialog(){
        val builder = AlertDialog.Builder(this)
        val costumeAlertDialog = LayoutInflater.from(this).inflate(R.layout.dialog_success,null)
        registerViewModel.registerResponse.observe(this){
            if (!it.success){
                showToast(it.message)
            }else{
                builder.setView(costumeAlertDialog)
                val btnClose = costumeAlertDialog.findViewById<Button>(R.id.btn_dismiss)
                btnClose.setOnClickListener {
                    val intentLogin = Intent(this, LoginActivity::class.java)
                    startActivity(intentLogin)
                    finish()
                }
                val dialog = builder.create()
                dialog.show()
            }
        }
    }

    private fun showToast(message: String){
        Toast.makeText(this,message,Toast.LENGTH_LONG).show()
    }
}