package com.example.dinospizza


import android.app.DownloadManager
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley

import com.example.dinospizza.databinding.ActivityRegistroBinding


class Registro : AppCompatActivity() {
    private  lateinit var binding: ActivityRegistroBinding
    private var connectSql = Connect()



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegistroBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        inicio()
    }

    fun inicio() {
        binding.btnInicio.setOnClickListener {
            if(Reglas.Validarnom(binding.editRegname1.text.toString()) == 1){
                if(Reglas.Validarnom(binding.editRegcorreo1.text.toString()) == 1){
                    if(Reglas.valiPas(binding.editContra1.text.toString()) == 1){
                        if(Reglas.valiPas(binding.editConf1.text.toString()) == 1){
                            if(binding.editContra1.text.toString().equals(binding.editConf1.text.toString())){
                                val url="http://" +  App.ip + "/developerGre/validar_usuario.php"
                                val queue=Volley.newRequestQueue(this)
                                var resultadoPost = object : StringRequest(Request.Method.POST,url,
                                    Response.Listener<String> { response ->
                                        Toast.makeText(this,"Usuario insertado exitosamente",Toast.LENGTH_LONG).show()
                                        startActivity(Intent(applicationContext, Login::class.java))
                                        finish()

                                    },Response.ErrorListener { error ->
                                        Toast.makeText(this,"Error $error ",Toast.LENGTH_LONG).show()
                                    }){
                                    override fun getParams(): MutableMap<String, String> {
                                        val parametros=HashMap<String,String>()
                                        parametros.put("usuario",binding.editRegname1.text.toString())
                                        parametros.put("correo",binding.editRegcorreo1.text.toString())
                                        parametros.put("contra1",binding.editContra1.text.toString())
                                        parametros.put("contra2",binding.editConf1.text.toString())
                                        return parametros
                                    }
                                }
                                queue.add(resultadoPost)

                            }else{
                                Toast.makeText(applicationContext,"No coiciden las contraseñas",Toast.LENGTH_LONG)
                            }
                        }else{
                            binding.editConf1.setError("Minimo: (A-Z) - (a-z) - (0-9) / no este vacio el campo / minimo 8 caracter")
                        }
                    }else{
                        binding.editContra1.setError("Minimo: (A-Z) - (a-z) - (0-9) / no este vacio el campo / minimo 8 caracter")
                    }
                }else{
                    binding.editRegcorreo1.setError("@example.com")
                }
            }else{
                binding.editRegname1.setError("\"Esta vacío el campo / debe contener 3 caracter\"")
            }
        }

    }
    private fun alert(){
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Error")
        builder.setMessage("Fallo de registro / Sin conexión")
        builder.setPositiveButton("Aceptar", null)
        val nue = builder.create()
        nue.show()
    }
}