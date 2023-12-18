package id.wildexplorerscompanion.ui.plantdetail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import id.wildexplorerscompanion.R
import id.wildexplorerscompanion.databinding.ActivityPlantDetailBinding

class PlantDetailActivity : AppCompatActivity() {
    private lateinit var detailBinding: ActivityPlantDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        detailBinding = ActivityPlantDetailBinding.inflate(layoutInflater)
        setContentView(detailBinding.root)

        supportActionBar?.title = getString(R.string.detail_plant)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }
}