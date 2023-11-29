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
import id.wildexplorerscompanion.R
import id.wildexplorerscompanion.databinding.ActivityLoginBinding
import id.wildexplorerscompanion.ui.home.MainActivity

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        singUpText()
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
}