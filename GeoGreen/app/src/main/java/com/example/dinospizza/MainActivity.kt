package com.example.dinospizza

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.SnapHelper
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.dinospizza.databinding.ActivityMainBinding
import org.json.JSONObject


class MainActivity : AppCompatActivity() {


    private var titlelist = mutableListOf<String>()
    private var subtitle = mutableListOf<String>()
    private var imagenlist = mutableListOf<Int>()
    private var imagencad = mutableListOf<Int>()
    private var cos = mutableListOf<String>()


    private var oftitlelist = mutableListOf<String>()
    private var ofsubtitle = mutableListOf<String>()
    private var ofimagencad = mutableListOf<Int>()
    private var ofcos = mutableListOf<String>()
    private var ofcali = mutableListOf<String>()
    private var ofdes = mutableListOf<String>()
    private var oflim = mutableListOf<String>()

    private var titlemap = mutableListOf<String>()
    private var submap= mutableListOf<String>()
    private var imgmap= mutableListOf<Int>()
    private var arraymap = arrayOf("Campeche", "Merida", "Cancun", "Chiapas", "Tabasco")
    lateinit var context: Context

    private  lateinit var binding: ActivityMainBinding

    private var array2 = arrayOf("Paquete 1", "Paquete 2","Paquete 3", "Paquete 4")
    private var arrayimgOf = arrayOf(R.drawable.o1,R.drawable.o2,R.drawable.o3,R.drawable.o4)



    private var array = arrayOf("Planta 1", "Planta 2", "Planta 3", "Planta 4")
    private var arrayimg = arrayOf(R.drawable.c1,R.drawable.c2,R.drawable.c3,R.drawable.c4)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        if(testEstadoSesion()){
            var correo : String? = getSharedPreferences("correo", Context.MODE_PRIVATE).getString("correo","")

            //Toast.makeText(applicationContext,correo, Toast.LENGTH_SHORT).show()

            postList()
            postList2()
            postListmap()
            cargar()

        }







        binding.imageView.setOnClickListener {
            startActivity(Intent(applicationContext, MainPerfil::class.java))
        }
    }

    override fun onStart() {
        super.onStart()
        //Verificamos si ha iniciado sesion
        if(!testEstadoSesion()){
            setEstadoSesion(false)
            val iLogoin = Intent(this,Login::class.java)
            startActivity(iLogoin)
            finish()
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
    private fun addTolist(title: String, costo: String, descripcion: String, i: Int){
        if(App.incre <= array.size){
            titlelist.add(title)
            subtitle.add(descripcion)
            cos.add(costo)
            imagencad.add(i)

        }
        App.title2 = titlelist
        App.subtitle2 = subtitle
        App.cos2 = cos
        App.image2 = imagencad

    }

    private fun postList(){
        var title = ""
        var sub = ""
        var cos = ""



        for (i in array) {
            val url =  "http://${App.ip}/developerGre/getFood.php?title=$i"

            val queue= Volley.newRequestQueue(this)
            val resultadoPost = JsonObjectRequest(
                Request.Method.GET,
                url,
                null,
                Response.Listener<JSONObject> { response ->
                    try{
                        title = response.getString("title")
                        cos = response.getString("costo")
                        sub = response.getString("subtitle")
                        addTolist("$title", "$cos", "$sub", arrayimg[App.incre])
                        App.incre += 1


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
    private fun addTolist2(title: String, costo: String, descripcion: String,cali: String,descuento: String,limite: String, i: Int){
        if(App.incre <= array.size){
            oftitlelist.add(title)
            ofsubtitle.add(descripcion)
            ofcos.add(costo)
            ofimagencad.add(i)
            ofcali.add(cali)
            ofdes.add(descuento)
            oflim.add(limite)

        }
        App.Oftitle2 = oftitlelist
        App.Ofsubtitle = ofsubtitle
        App.Ofcos = ofcos
        App.Ofcali = ofcali
        App.Ofdes = ofdes
        App.Oflim = oflim
        App.Ofimagencad = ofimagencad
    }
    private fun postList2(){
        var title = ""
        var sub = ""
        var cos = ""
        var cali = ""
        var descuento = ""
        var limite = ""



        for (i in array2) {
            val url =  "http://${App.ip}/developerGre/getPaquete.php?title=$i"

            val queue= Volley.newRequestQueue(this)
            val resultadoPost = JsonObjectRequest(
                Request.Method.GET,
                url,
                null,
                Response.Listener<JSONObject> { response ->
                    try{
                        title = response.getString("title")
                        cos = response.getString("costo")
                        sub = response.getString("subtitle")
                        cali = response.getString("cali")
                        descuento = response.getString("descuento")
                        limite = response.getString("limite")
                        addTolist2("$title", "$cos", "$sub", "$cali", "$descuento", "$limite",arrayimg[App.incre])
                        App.incre2 += 1


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

    private fun addTolistmap(title : String, descripcion : String,  imagenm : Int){
        titlemap.add(title)
        submap.add(descripcion)
        imgmap.add(imagenm)
    }
    private fun postListmap(){
        var  des = "Los lugares mas visitados"
        for (i in arraymap){
            App.incremap += 1
            addTolistmap("$i",  "$des", R.drawable.iconmin)
        }
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
            refres()
            ref()
        }, 2000)
    }
    fun refres(){

        var arrayimg2 = arrayOf(R.drawable.c1,R.drawable.c2,R.drawable.c3,R.drawable.c4)

        val recyclerView = findViewById<RecyclerView>(R.id.popular_recycler)
        val adapter = CustomAdapter(titlelist, subtitle,cos, arrayimg2)
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        recyclerView.adapter = adapter

        val recyclerViewmap = findViewById<RecyclerView>(R.id.list_map)
        val adaptermap = ReciclerAdapter(titlemap, submap, imgmap)
        recyclerViewmap.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        recyclerViewmap.adapter = adaptermap

        val snapHelper: SnapHelper = LinearSnapHelper()
        snapHelper.attachToRecyclerView(recyclerViewmap)




    }
    fun ref()
    {
        var arrayimgOf = arrayOf(R.drawable.o1,R.drawable.o2,R.drawable.o3,R.drawable.o4)

        val ofrecyclerView = findViewById<RecyclerView>(R.id.all_menu_recycler)
        val ofadapter = CustomOferta(arrayimgOf,oftitlelist,ofsubtitle,ofcos,ofcali,ofdes,oflim)
        ofrecyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        ofrecyclerView.adapter = ofadapter
    }
}