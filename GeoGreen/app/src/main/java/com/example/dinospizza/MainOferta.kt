package com.example.dinospizza

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.widget.Toast
import com.example.dinospizza.databinding.ActivityMainOfertaBinding
import com.example.dinospizza.databinding.ActivityMainPerfilBinding
import com.google.zxing.BarcodeFormat
import com.google.zxing.qrcode.QRCodeWriter

class MainOferta : AppCompatActivity() {
    lateinit var nombreComida : String
    private  lateinit var binding: ActivityMainOfertaBinding
    lateinit var posi : String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainOfertaBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )

        this.window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_FULLSCREEN
                or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY)

        var correo : String? = getSharedPreferences("correo", Context.MODE_PRIVATE).getString("correo","")

        var datosdoc = intent

        posi = datosdoc.getStringExtra("pos").toString()
        var cadena = "-" +  correo + ":" + App.Oftitle2[posi.toInt()]
        Toast.makeText(applicationContext,cadena+ "", Toast.LENGTH_SHORT).show()
        val write = QRCodeWriter()

        try {
            val bitMatrix = write.encode(cadena, BarcodeFormat.QR_CODE,512,512 )
            val width = bitMatrix.width
            val heigth = bitMatrix.height
            val bmp = Bitmap.createBitmap(width,heigth,Bitmap.Config.RGB_565)
            for(x in 0 until width){
                for(y in 0 until heigth){
                    bmp.setPixel(x,y, if(bitMatrix[x,y]) Color.BLACK else Color.WHITE)
                }
            }
            binding.tvHeader.setImageBitmap(bmp)
        }catch (e:Exception){
            e.printStackTrace()
        }
    }
}