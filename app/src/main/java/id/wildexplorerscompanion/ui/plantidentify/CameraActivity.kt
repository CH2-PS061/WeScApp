package id.wildexplorerscompanion.ui.plantidentify

import android.Manifest
import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.media.ThumbnailUtils
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import id.wildexplorerscompanion.R
import id.wildexplorerscompanion.ml.Model
import id.wildexplorerscompanion.ui.home.HomeActivity
import id.wildexplorerscompanion.ui.plantdetail.PlantDetailActivity
import org.tensorflow.lite.DataType
import org.tensorflow.lite.support.tensorbuffer.TensorBuffer
import java.io.ByteArrayOutputStream
import java.io.IOException
import java.nio.ByteBuffer
import java.nio.ByteOrder

class CameraActivity : AppCompatActivity() {
    private lateinit var camera: LinearLayout
    private lateinit var gallery: LinearLayout
//    var imageView: ImageView? = null
//    var result: TextView? = null
//    var confidence: TextView? = null
    var imageSize = 300
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_camera)
        camera = findViewById(R.id.btn_kamera)
        gallery = findViewById(R.id.btn_gallery)
        camera!!.setOnClickListener {
            if (checkSelfPermission(Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
                val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                startActivityForResult(cameraIntent, 3)
            } else {
                requestPermissions(arrayOf<String>(Manifest.permission.CAMERA), 100)
            }
        }
        gallery!!.setOnClickListener {
            val cameraIntent =
                Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            startActivityForResult(cameraIntent, 1)
        }
        supportActionBar?.title = "Identifikasi Tanaman"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }
    private fun succesDialog(plantname: String, confidence: Float, image: Bitmap) {
        val view = layoutInflater.inflate(R.layout.dialog_scan_result, null)
        val builder = AlertDialog.Builder(this@CameraActivity)
        builder.setView(view)
        val dialog = builder.create()
        dialog.show()
        dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
        val btnDetail = view.findViewById<Button>(R.id.btn_detail)
        if (confidence >= 0.9 ){
            view.findViewById<LinearLayout>(R.id.layout_warning).visibility = View.GONE
            val bitmap: Bitmap = image
            val stream = ByteArrayOutputStream()
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream)
            val byteArray = stream.toByteArray()
            btnDetail.setOnClickListener {
                val intent = Intent(this, PlantDetailActivity::class.java)
                intent.putExtra("KEY",plantname)
                intent.putExtra("IMG",byteArray)
                startActivity(intent)
            }
        } else {
            btnDetail.visibility = View.GONE
        }

        view.findViewById<TextView>(R.id.tv_result).text = plantname
        view.findViewById<TextView>(R.id.tv_confidence).text =
            String.format("%.1f%%", confidence* 100 )
        view.findViewById<ImageView>(R.id.iv_result).setImageBitmap(image)
        view.findViewById<Button>(R.id.btn_cancel).setOnClickListener {
            dialog.dismiss()
            val intentToHome = Intent(this@CameraActivity, HomeActivity::class.java)
            intentToHome.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(intentToHome)
            finish()
        }
    }
    fun classifyImage(image: Bitmap) {
        try {
            val model: Model = Model.newInstance(getApplicationContext())

            // Creates inputs for reference.
            val inputFeature0: TensorBuffer =
                TensorBuffer.createFixedSize(intArrayOf(1, 300, 300, 3), DataType.FLOAT32)
            val byteBuffer = ByteBuffer.allocateDirect(4 * imageSize * imageSize * 3)
            byteBuffer.order(ByteOrder.nativeOrder())
            val intValues = IntArray(imageSize * imageSize)
            image.getPixels(intValues, 0, image.width, 0, 0, image.width, image.height)
            var pixel = 0
            //iterate over each pixel and extract R, G, and B values. Add those values individually to the byte buffer.
            for (i in 0 until imageSize) {
                for (j in 0 until imageSize) {
                    val `val` = intValues[pixel++] // RGB
                    byteBuffer.putFloat((`val` shr 16 and 0xFF) * (1f / 1))
                    byteBuffer.putFloat((`val` shr 8 and 0xFF) * (1f / 1))
                    byteBuffer.putFloat((`val` and 0xFF) * (1f / 1))
                }
            }
            inputFeature0.loadBuffer(byteBuffer)

            // Runs model inference and gets result.
            val outputs: Model.Outputs = model.process(inputFeature0)
            val outputFeature0: TensorBuffer = outputs.getOutputFeature0AsTensorBuffer()
            val confidences: FloatArray = outputFeature0.getFloatArray()
            // find the index of the class with the biggest confidence.
            var maxPos = 0
            var maxConfidence = 0f
            for (i in confidences.indices) {
                if (confidences[i] > maxConfidence) {
                    maxConfidence = confidences[i]
                    maxPos = i
                }
            }
            val classes = arrayOf("alang-alang", "begonia", "buah-merbei", "cantigi-gunung", "ciplukan", "daun-senggani", "honje", "lumut-hati", "paku-sayur", "pohpohan", "rane", "semanggi")
//            result!!.text = classes[maxPos]
//            confidence!!.text = String.format("%.1f%%", maxConfidence*100)

            succesDialog(classes[maxPos], maxConfidence, image)
            // Releases model resources if no longer used.
            model.close()
        } catch (e: IOException) {
            // TODO Handle the exception
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == 3) {
                var image = data?.getExtras()?.get("data") as Bitmap
                val dimension = Math.min(image.width, image.height)
                image = ThumbnailUtils.extractThumbnail(image, dimension, dimension)
//                imageView!!.setImageBitmap(image)
                image = Bitmap.createScaledBitmap(image, imageSize, imageSize, false)
                classifyImage(image)
            } else {
                val dat: Uri? = data?.getData()
                var image: Bitmap? = null
                try {
                    image = MediaStore.Images.Media.getBitmap(this.getContentResolver(), dat)
                } catch (e: IOException) {
                    e.printStackTrace()
                }
//                imageView?.setImageBitmap(image)
                image = Bitmap.createScaledBitmap(image!!, imageSize, imageSize, false)
                classifyImage(image)
            }
        }
    }
}
