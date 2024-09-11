package com.example.awoxapp.detailedDevicesActivity

import android.graphics.Bitmap
import android.graphics.pdf.PdfRenderer
import android.os.Bundle
import android.os.ParcelFileDescriptor
import android.util.Log
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.awoxapp.R
import com.example.awoxapp.databinding.ActivityUsingGuideBinding
import com.github.barteksc.pdfviewer.PDFView
import java.io.File
import java.io.IOException

class ActivityUsingGuide : AppCompatActivity() {

    private lateinit var mBinding: ActivityUsingGuideBinding
    private lateinit var pdfView: PDFView


    private fun backButtonClicked(){

        mBinding.backButton.setOnClickListener { finish() }

    }
    private fun showPDF() {
        pdfView = mBinding.pdfview

        try {
            pdfView.fromAsset("guide.pdf").load()
            Log.i("ActivityUsingGuide", "loading PDF successful")
        } catch (e: Exception) {
            Log.e("ActivityUsingGuide", "Error loading PDF: ${e.message}")
        }
    }


//
//    private fun copyPdfFromAssets() {
//        val fileName = "guide.pdf"
//        val file = File(filesDir, fileName)
//
//        // Dosya zaten mevcutsa, tekrar kopyalamaya gerek yok
//        if (!file.exists()) {
//            try {
//                val inputStream = assets.open(fileName)
//                val outputStream = file.outputStream()
//                inputStream.copyTo(outputStream)
//                inputStream.close()
//                outputStream.close()
//                Log.d("ActivityUsingGuide", "Dosya başarıyla kaydedildi: ${file.absolutePath}")
//            } catch (e: IOException) {
//                e.printStackTrace()
//                Log.e("ActivityUsingGuide", "Dosya kaydedilirken hata oluştu: ${e.message}")
//            }
//        } else {
//            Log.d("ActivityUsingGuide", "Dosya zaten mevcut: ${file.absolutePath}")
//        }
//    }
//
//
//    private fun checkIfFileExists() {
//        val fileName = "guide.pdf"
//        val file = File(filesDir, fileName)
//
//        if (file.exists()) {
//            Log.d("ActivityUsingGuide", "Dosya mevcut: ${file.absolutePath}")
//        } else {
//            Log.e("ActivityUsingGuide", "Dosya bulunamadı: ${file.absolutePath}")
//        }
//    }
//
//    private fun pdfView(){
//
//        val fileName = "guide.pdf"
//        val file = File(filesDir, fileName)
//
//        val input = ParcelFileDescriptor.open(file,ParcelFileDescriptor.MODE_READ_ONLY)
//        val renderer = PdfRenderer(input)
//
//       for (i in 0 until renderer.pageCount){
//
//           val page = renderer.openPage(i)
//           val bitmapWidth = resources.displayMetrics.densityDpi / 72 * page.width
//           val bitmapHeight = resources.displayMetrics.densityDpi / 72 * page.height
//
//           //convert the pdf to bitmap
//           val bitmap = Bitmap.createBitmap(bitmapWidth, bitmapHeight, Bitmap.Config.ARGB_8888)
//           page.render(bitmap, null, null, PdfRenderer.Page.RENDER_MODE_FOR_DISPLAY)
//
//           page.close()
//
//           val imageView = ImageView(this)
//           imageView.setImageBitmap(bitmap)
//           mBinding.container.addView(imageView)
//
//
//
//        }
//
//        renderer.close()






    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_using_guide)


        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_using_guide)

        //pdfView()
//        copyPdfFromAssets()
//        checkIfFileExists()
//        pdfView()

        showPDF()
        backButtonClicked()



    }
}