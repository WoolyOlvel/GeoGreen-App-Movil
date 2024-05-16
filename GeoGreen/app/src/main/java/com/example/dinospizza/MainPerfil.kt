package com.example.dinospizza

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.dinospizza.databinding.ActivityMainBinding
import com.example.dinospizza.databinding.ActivityMainPerfilBinding
import org.json.JSONObject


class MainPerfil : AppCompatActivity() {
    private  lateinit var binding: ActivityMainPerfilBinding

    private var datos = arrayOf("","","","","")
    lateinit var correo2 : String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainPerfilBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        var correo : String? = getSharedPreferences("correo", Context.MODE_PRIVATE).getString("correo","")
        correo2 = correo!!
        binding.textView2.text = correo.toString()
        leer(correo)
        binding.btnCerrar.setOnClickListener {
            cargar()
        }
        binding.button2.setOnClickListener {
            startActivity(Intent(applicationContext, MainPedidos::class.java))
        }
        binding.imageView2.setOnClickListener {
            QuantityDialog(
                onSubmitClickListener = { quantity ->
                    if(quantity.length > 10){

                        cargar2(quantity)
                    }else{
                        Toast.makeText(applicationContext, "URL INVALIDO", Toast.LENGTH_SHORT).show()
                    }
                }
            ).show(supportFragmentManager, "dialog")
        }
    }



    fun testEstadoSesion():Boolean{
        val pref: SharedPreferences = getSharedPreferences(
            sharePreferenceSesion, Context.MODE_PRIVATE
        )
        return pref.getBoolean(testSesion,false)
    }

    fun setEstadoSesion(newStatus:Boolean){
        val pref: SharedPreferences = getSharedPreferences(
            sharePreferenceSesion, Context.MODE_PRIVATE
        )
        val editor = pref.edit()
        editor.putBoolean(testSesion,newStatus)
        editor.commit()
    }
    fun cargar(){
        val builder = AlertDialog.Builder(this)
        val dialogView = layoutInflater.inflate(R.layout.progress_dialog, null)
        builder.setView(dialogView)
        builder.setCancelable(false)
        var dialog = builder.create()
        dialog.show()
        Handler().postDelayed({
            dialog.dismiss()
            setEstadoSesion(false)
            startActivity(Intent(applicationContext, Login::class.java))
            finish()
        }, 5000)
    }
    fun cargar2(quantity: String){
        val builder = AlertDialog.Builder(this)
        val dialogView = layoutInflater.inflate(R.layout.progress_dialog, null)
        builder.setView(dialogView)
        builder.setCancelable(false)
        var dialog = builder.create()
        dialog.show()
        Handler().postDelayed({
            dialog.dismiss()


        }, 2000)
    }
    fun leer(correo: String) {
        val url =  "http://${App.ip}/developerGre/obtener_usuario.php?correo=$correo"

        val queue= Volley.newRequestQueue(this)
        val resultadoPost = JsonObjectRequest(
            Request.Method.GET,
            url,
            null,
            Response.Listener<JSONObject> { response ->
                try{
                    binding.textView.text = response.getString("usuario")
                }catch (ex : Exception ){
                    ex.printStackTrace()

                }
            },
            Response.ErrorListener { error ->
                Toast.makeText(this, "ERROR", Toast.LENGTH_LONG).show()
            }

        )
        queue.add(resultadoPost)
    }
}