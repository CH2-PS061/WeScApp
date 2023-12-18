package id.wildexplorerscompanion.ui.home


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.viewmodel.viewModelFactory
import id.wildexplorerscompanion.R
import id.wildexplorerscompanion.databinding.ActivityHomeBinding
import id.wildexplorerscompanion.ui.ViewModelFactory
import id.wildexplorerscompanion.ui.login.LoginActivity
import id.wildexplorerscompanion.ui.profile.ProfileActivity
import id.wildexplorerscompanion.ui.plantidentify.CameraActivity

class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding
    private val homeViewModel by viewModels<HomeViewModel> {
        ViewModelFactory.getInstance(this)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        homeViewModel.getSession().observe(this) {
            val getName = it.name
            if (it.isLogin){
                binding.tvHomeName.text = getName
            } else{
                startActivity(Intent(this, LoginActivity::class.java))
                finish()
            }
        }

        binding.ivHomeImage.setOnClickListener {
            startActivity(Intent(this, ProfileActivity::class.java))
        }
        binding.layoutPlant.setOnClickListener {
            val intent = Intent(this@HomeActivity, CameraActivity::class.java)
            startActivity(intent)
        }

    }
}