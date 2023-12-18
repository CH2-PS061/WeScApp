package id.wildexplorerscompanion.ui.plantdetail

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import id.wildexplorerscompanion.R
import id.wildexplorerscompanion.databinding.ActivityPlantDetailBinding
import id.wildexplorerscompanion.ui.ViewModelFactory

class PlantDetailActivity : AppCompatActivity() {
    private lateinit var detailBinding: ActivityPlantDetailBinding
    private val detailViewModel by viewModels<DetailViewModel> {
        ViewModelFactory.getInstance(this)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        detailBinding = ActivityPlantDetailBinding.inflate(layoutInflater)
        setContentView(detailBinding.root)

        supportActionBar?.title = getString(R.string.detail_plant)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val getExtra = intent.getStringExtra("KEY")
        var getIMG = intent.getByteArrayExtra("IMG")
        val imageBitmap = BitmapFactory.decodeByteArray(getIMG,0,getIMG!!.size)
        detailViewModel.getById(getExtra.toString()).observe(this){
            detailBinding.tvPlantDetail.text = it.name
            detailBinding.tvPlantDescription.text = it.description
            detailBinding.ivDetailPlant.setImageBitmap(imageBitmap)
        }

//        detailViewModel.getAllPlant().observe(this){
//            it.map {
//                detailBinding.tvPlantDetail.text = it.name
//                detailBinding.tvPlantDescription.text = it.description
//            }
//        }
    }
}