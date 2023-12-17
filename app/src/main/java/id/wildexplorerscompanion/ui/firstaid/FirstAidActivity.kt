package id.wildexplorerscompanion.ui.firstaid

import android.content.res.AssetManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.ui.platform.ViewCompositionStrategy
import com.mukesh.MarkDown
import id.wildexplorerscompanion.R
import id.wildexplorerscompanion.databinding.ActivityFirstAidBinding
import java.io.File
import java.net.URL
import java.util.Properties

class FirstAidActivity : AppCompatActivity() {
    private lateinit var firstAidBinding: ActivityFirstAidBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        firstAidBinding = ActivityFirstAidBinding.inflate(layoutInflater)
        setContentView(firstAidBinding.root)
        supportActionBar?.title = getString(R.string.first_aid_guide)

        firstAidBinding.layoutFirstAidMarkdown.apply {
            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
            setContent {
                MaterialTheme{
                    val file = assets.open("Cara.md")
                    MarkDown(file =File("file:///main/assets/Cara.md") )
                }
            }
        }
    }
}