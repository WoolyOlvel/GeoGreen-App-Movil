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
import com.example.dinospizza.databinding.ActivityLoginBinding
import org.json.JSONObject

class Login : AppCompatActivity() {
    lateinit var context: Context

    private var connectSql = Connect()
    private  lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        binding.btnRegis.setOnClickListener {
            startActivity(Intent(applicationContext, Registro::class.java))

        }


        binding.btnInicio.setOnClickListener {
            if(Reglas.Validarnom(binding.editCorreo1.text.toString()) == 1 && Reglas.valiPas(binding.editContra1.text.toString()) == 1 ){
                leer(binding.editCorreo1.text.toString(),binding.editContra1.text.toString())
            }else{
                Toast.makeText(applicationContext, "Correo no valido / Password no valido", Toast.LENGTH_SHORT).show()
            }


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
    fun cargar(email: String){
        val builder = AlertDialog.Builder(this)
        val dialogView = layoutInflater.inflate(R.layout.progress_dialog, null)
        builder.setView(dialogView)
        builder.setCancelable(false)
        var dialog = builder.create()
        dialog.show()
        Handler().postDelayed({
            dialog.dismiss()
            startActivity(Intent(applicationContext, MainActivity::class.java))
            finish()
        }, 5000)
    }
    fun setName(newStatus: String, share: SharedPreferences) {
        val editor = share.edit()
        editor.putString("correo",newStatus)
        editor.commit()
    }
    fun leer(correo: String,contra: String) {
        val url =  "http://${App.ip}/developerGre/obtener_usuario.php?correo=$correo"
        val queue= Volley.newRequestQueue(this)
        val resultadoPost = JsonObjectRequest(
            Request.Method.GET,
            url,
            null,
            Response.Listener<JSONObject> { response ->
                try{
                    if(contra.equals(response.getString("contra1"))){
                        setEstadoSesion(true)
                        Toast.makeText(applicationContext,"SESION INICIADA", Toast.LENGTH_SHORT).show()
                        setName(binding.editCorreo1.text.toString(),getSharedPreferences("correo", Context.MODE_PRIVATE))
                        startActivity(Intent(applicationContext, MainActivity::class.java))
                        finish()
                    }else{
                        Toast.makeText(this, "Password invalido", Toast.LENGTH_LONG).show()

                    }
                }catch (ex : Exception ){
                    ex.printStackTrace()

                }
            },
            Response.ErrorListener { error ->
                Toast.makeText(this, "Correo no valido", Toast.LENGTH_LONG).show()
            }

        )
        queue.add(resultadoPost)
    }
}

