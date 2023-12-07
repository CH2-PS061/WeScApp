package id.wildexplorerscompanion.ui.resetpassword

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import id.wildexplorerscompanion.R
import id.wildexplorerscompanion.databinding.ActivityResetPasswordBinding

class ResetPasswordActivity : AppCompatActivity() {
    private lateinit var resetBinding: ActivityResetPasswordBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        resetBinding = ActivityResetPasswordBinding.inflate(layoutInflater)
        setContentView(resetBinding.root)

        //Untuk Top NavBar
        supportActionBar?.title = getString(R.string.change_password)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    // TODO: Fix Unutk Kembali Ke profile Activity 
    override fun onContextItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            
        }
        return super.onContextItemSelected(item)
    }
}