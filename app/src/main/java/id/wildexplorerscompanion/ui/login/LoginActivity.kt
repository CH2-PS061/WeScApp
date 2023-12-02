package id.wildexplorerscompanion.ui.login

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.TextPaint
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import id.wildexplorerscompanion.R
import id.wildexplorerscompanion.databinding.ActivityLoginBinding
import id.wildexplorerscompanion.ui.ViewModelFactory
import id.wildexplorerscompanion.ui.home.MainActivity

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private val loginViewModel by viewModels<LoginViewModel> {
        ViewModelFactory.getInstance(this)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        singUpText()

        binding.btnLogin.setOnClickListener {
            val email = binding.edtTextEmail.text.toString()
            val password = binding.edtTextPassword.text.toString()
            loginViewModel.getLogin(email, password)
        }

        loginViewModel.loginResponse.observe(this){

        }
    }


    private fun singUpText(){
        val text ="Belum punya akun? Daftar Sekarang!"
        val spanString = SpannableString(text)

        val clickAbleSpan = object : ClickableSpan(){
            override fun onClick(widget: View) {
                val intent = Intent(this@LoginActivity,MainActivity::class.java)
                widget.context.startActivity(intent)
            }

            override fun updateDrawState(ds: TextPaint) {
                ds.isUnderlineText = false
                ds.color = getColor(R.color.applegreen)

            }
        }
        spanString.setSpan(clickAbleSpan,18,34,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)

        binding.tvSingup.text = spanString
        binding.tvSingup.highlightColor = Color.TRANSPARENT
        binding.tvSingup.movementMethod = LinkMovementMethod.getInstance()
    }

    private fun showToast(message: String){
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show()
    }
}