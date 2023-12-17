package id.wildexplorerscompanion.ui.survivalguide

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.compose.material.MaterialTheme
import androidx.compose.ui.platform.ViewCompositionStrategy
import com.mukesh.MarkDown
import id.wildexplorerscompanion.R
import id.wildexplorerscompanion.databinding.ActivitySurvivalGuideBinding
import java.net.URL

class SurvivalGuideActivity : AppCompatActivity() {
    private lateinit var survivalGuideBinding: ActivitySurvivalGuideBinding
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        survivalGuideBinding = ActivitySurvivalGuideBinding.inflate(layoutInflater)
        setContentView(survivalGuideBinding.root)
        supportActionBar?.title = getString(R.string.survival_guide)
        
        survivalGuideBinding.layoutSurvivalMarkdown.apply { 
            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
            setContent { 
                MaterialTheme{
                    MarkDown(url = URL("https://raw.githubusercontent.com/CH2-PS061/markdown/main/Survival%20Guide/Cari%20dan%20Membersihkan%20Air.md"))
                }
            }
        }
    }
}