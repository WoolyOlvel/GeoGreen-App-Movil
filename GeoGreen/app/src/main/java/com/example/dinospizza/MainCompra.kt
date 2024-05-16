package com.example.dinospizza

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.dinospizza.databinding.ActivityMainCompraBinding
import com.squareup.picasso.Picasso


class MainCompra : AppCompatActivity() {

    private var c = mutableListOf<String>()


    lateinit var posi : String
    private  lateinit var binding: ActivityMainCompraBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainCompraBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        //Hide status bar and navigation bar at the bottom
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
        var datosdoc = intent

        posi = datosdoc.getStringExtra("pos").toString()
        var correo  = getSharedPreferences("correo", Context.MODE_PRIVATE).getString("correo","").toString()

        Picasso.get().load(App.image2[posi.toInt()]).into(binding.custom1Img)
        binding.loginText.text = App.title2[posi.toInt()]
        binding.textPun.text = "Calificacion: " + App.subtitle2[posi.toInt()]
        binding.textCos.text = "Costo: $" +App.cos2[posi.toInt()]
        //Toast.makeText(applicationContext,nombreComida+ "", Toast.LENGTH_SHORT).show()
        binding.btnCompra.setOnClickListener {
            cargar(correo)
        }
    }

    fun cargar(correo: String) {
        val builder = AlertDialog.Builder(this)
        val dialogView = layoutInflater.inflate(R.layout.progress_dialog, null)
        builder.setView(dialogView)
        builder.setCancelable(false)
        var dialog = builder.create()
        dialog.show()
        Handler().postDelayed({
            dialog.dismiss()
            val url="http://" +  App.ip + "/developerGre/addCompra.php"
            val queue= Volley.newRequestQueue(this)
            var resultadoPost = object : StringRequest(Request.Method.POST,url,
                Response.Listener<String> { response ->
                    Toast.makeText(this,"Compra exitosa",Toast.LENGTH_LONG).show()
                    startActivity(Intent(applicationContext, MainPedidos::class.java))


                }, Response.ErrorListener { error ->
                    Toast.makeText(this,"Error $error ",Toast.LENGTH_LONG).show()
                }){
                override fun getParams(): MutableMap<String, String> {
                    val parametros=HashMap<String,String>()
                    parametros.put("producto",binding.loginText.text.toString())
                    parametros.put("correo",correo)

                    return parametros
                }
            }
            queue.add(resultadoPost)
        }, 2000)
    }
}