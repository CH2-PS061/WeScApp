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
import android.view.View
import id.wildexplorerscompanion.R
import id.wildexplorerscompanion.databinding.ActivityRegisterBinding
import id.wildexplorerscompanion.ui.login.LoginActivity

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding : ActivityRegisterBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        signInText()
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
}