package id.wildexplorerscompanion.ui.firstaid

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import id.wildexplorerscompanion.R
import id.wildexplorerscompanion.databinding.ActivityFirstAidBinding



class FirstAidActivity : AppCompatActivity() {
    private lateinit var firstAidBinding: ActivityFirstAidBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        firstAidBinding = ActivityFirstAidBinding.inflate(layoutInflater)
        setContentView(firstAidBinding.root)
        supportActionBar?.title = getString(R.string.first_aid_guide)

        firstAidBinding.webView.settings.javaScriptEnabled= true
        firstAidBinding.webView.setBackgroundColor(resources.getColor(R.color.hintgreen))
        firstAidBinding.webView.loadUrl("file:///android_asset/FirstAidGuide.html")


    }
}