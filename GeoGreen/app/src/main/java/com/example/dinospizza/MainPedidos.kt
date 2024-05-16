package com.example.dinospizza

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.dinospizza.databinding.ActivityMainPedidosBinding
import org.json.JSONObject

class MainPedidos : AppCompatActivity() {

    var correo = ""
    var gcomid = mutableListOf<String>()
    var gprecio = mutableListOf<String>()
    var glink =mutableListOf<String>()
    var gnombre = mutableListOf<String>()
    var precioR = 0.0
    var gopc =mutableListOf<String>()
    var correo2 = ""
    private var titlelist = mutableListOf<String>()

    private var cos = mutableListOf<String>()
    private  lateinit var binding: ActivityMainPedidosBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainPedidosBinding.inflate(layoutInflater)
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

        correo  = getSharedPreferences("correo", Context.MODE_PRIVATE).getString("correo","").toString()
        correo2 = correo
        leer(correo)

        postList()
        cargar(correo)
    }

    private fun postList(){

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

            refres(correo)

            binding.txtCoste.text = ""
        }, 4000)
    }
    fun refres(correo: String) {
        val recyclerView = findViewById<RecyclerView>(R.id.popular_recycler3)
        val adapter = CustomPedidos(titlelist,correo,cos,gopc)
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        recyclerView.adapter = adapter
    }
    fun leer(correo: String) {
        var name = ""
        var bol = true
        var ides = mutableListOf<String>()
        ides.add("")
        //correo=Tania@gmail.com&id=3
        for (i in 1..100) {
            val url =  "http://${App.ip}/developerGre/getCompra.php?correo=$correo&id=$i"
            val queue= Volley.newRequestQueue(this)
            val resultadoPost = JsonObjectRequest(
                Request.Method.GET,
                url,
                null,
                Response.Listener<JSONObject> { response ->
                    try{
                        if(correo.equals(response.getString("correo"))){
                            name = response.getString("producto")
                            leer2(name,correo)
                        }else{

                            Toast.makeText(this, "No hay compras", Toast.LENGTH_LONG).show()

                        }
                    }catch (ex : Exception ){
                        ex.printStackTrace()

                    }
                },
                Response.ErrorListener { error ->
                    Toast.makeText(this, "Error", Toast.LENGTH_LONG).show()
                }

            )
            queue.add(resultadoPost)
        }



    }

    private fun leer2(name: String, correo: String) {
        val url =  "http://${App.ip}/developerGre/getFood.php?title=$name"
        var title2 = ""
        var sub = ""
        var cos = ""
        val queue= Volley.newRequestQueue(this)
        val resultadoPost = JsonObjectRequest(
            Request.Method.GET,
            url,
            null,
            Response.Listener<JSONObject> { response ->
                try{
                    title2 = response.getString("title")
                    cos = response.getString("costo")

                    addTolist("$title2", "$correo", "$cos")

                    App.incre3 += 1


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

    private fun addTolist(s: String, s1: String, s2: String) {
        titlelist.add(s)
        cos.add(s2)
        App.title3 = titlelist
        App.cos3 = cos

    }
}