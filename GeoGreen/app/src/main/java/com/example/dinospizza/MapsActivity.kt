package com.example.dinospizza

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.example.dinospizza.databinding.ActivityMapsBinding
import com.google.android.gms.maps.model.BitmapDescriptorFactory

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private lateinit var binding: ActivityMapsBinding
    lateinit var valo : String
    private var lugaresCamp = arrayOf("Mario's Pizza", "Pizzeria La Pantera rosa", "Deaber PiZZAS Express","Pizzeria Mister Chiquini",
        "Pizzeria Durieell Campeche", "Pizzería Ninja TORTUGAS", "La Que Pizzas", "Dinno's Pizza Campeche", "La piazza Stuffed")
    private var cordenadasCam = arrayOf(19.8533004,-90.5315972, 19.812288,-90.5351728, 19.834247,-90.5476867, 19.8342916,-90.5630077, 19.839727969052024,-90.50824282368166, 19.8394357,-90.5454922,
        19.8296935,-90.5586434, 19.8264056,-90.5587163, 19.8239894,-90.5291708, 19.8327156,-90.5548426)

    private var lugaresMer = arrayOf("FISIOPRAXIA Rehabilitación y Fisioterapia sur Mérida Dolores Otero", "FISIO CARE", "Clínica de Mérida - Fisioterapia, Rehabilitación y Deporte","Clinica de Fisioterapia FISIOPLUS",
        "Fisiomer City / Recovery Center", "Physios Fisioterapia y Rehabilitación", "Fisiomer", "RehabilitaZone", "Fisio Mayab", "FisioCare Star Médica")
    private var cordenadasMer = arrayOf(20.9401344,-89.6250747, 21.0042177,-89.617988, 20.9859559,-89.6405753, 21.007485,-89.6582189, 21.0297258,-89.6059356, 20.9936742,-89.6341299,
        20.9974671,-89.6304136, 21.0089522,-89.6357764, 20.9904408,-89.6070281, 21.0161957,-89.5871899)

    private var lugaresCan = arrayOf("Fisioterapia Especializada by Therapik", "Core Crystal Fisioterapia Cancun", "Terapia Fisica en Cancun","KKYNESIUM TERAPIA FISICA Y REHABILITACION",
        "Fisiohealth rehabilitacion integral", "Heilenmed", "Terapia Fisica Cancun (Teramel)", "Terapia Física Quirúrgica del Sur", "Terapia Física y Rehabilitación Cancún", "Rehavity Cancùn")
    private var cordenadasCan = arrayOf(21.1377728,-86.8531382, 21.1659983,-86.8259361, 21.1737992,-86.8620382, 21.158249,-86.8300203, 21.1383238,-86.8735044, 21.1587241,-86.8319838,
        21.1591066,-86.8286971, 21.158695,-86.8594617, 21.1618327,-86.8370401, 21.1391122,-86.8541516)

    private var lugaresChia = arrayOf("REHABIMEDIX CLINICA DE REHABILITACION", "RD Fisioterapia", "Unidad De Terapia Fisica Y Rehabilitacion","Fisio Steph",
        "Fisiomedic", "Centro de Terapias y Medicina Natural", "Cirfe Centro Integral De Rehabilitación En Fisioterapia Especializada", "Jose Carmona Fisioterapia Especializada", "José Lindoro - Fisioterapia Integral", "IGL Fisioterapia")
    private var cordenadasChia = arrayOf(16.7517695,-93.0913478, 16.7501452,-93.1068476, 16.909319,-92.0850311, 14.9202733,-92.2579901, 14.9112088,-92.2738517, 16.759394,-93.1286669,
        14.8880995,-92.2639606, 16.7511492,-93.1330591, 16.2333582,-93.8968291, 16.7648309,-93.1227267)

    private var lugaresTabas = arrayOf("FIT. Fisioterapia Integral de Tabasco", "BodyRecovery Fisioterapia y Deporte S de RL de CV", "Centro de Fisioterapia Integral","Terapéutica del Golfo",
        "Fisio Medics", "Fisioterapia y Rehabilitación Física AC", "CIRF: Clínica Integral de Rehabilitación Física", "Fisioterapia GH", "UNIFIT", "Quirurgica Integral A.C.")
    private var cordenadasTabas = arrayOf(17.9747684,-92.9489352, 17.9732291,-92.9466317, 17.975812,-92.953363, 17.977842,-92.9121189, 18.2662049,-93.2261622, 17.9973917,-92.9295492,
        18.0047369,-92.9433642, 18.0615474,-93.1152473, 18.2664636,-93.2206996, 17.9780396,-92.9405822)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMapsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var datosdoc = intent
        valo = datosdoc.getStringExtra("valor").toString()

        Toast.makeText(applicationContext, valo, Toast.LENGTH_LONG).show()

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }


    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        when(valo){
            "0" ->{
                createMarkerCampeche()
            }
            "1" ->{
                createMarkerMerida()
            }
            "2" ->{
                createMarkerCancun()
            }
            "3" ->{
                createMarkerChiapas()
            }
            "4" ->{
                createMarkerTabasco()
            }else ->{ }
        }
    }
    private fun createMarkerCampeche() {
        // añade marcador
        val cordinates = LatLng(19.8448109, -90.5230914)
        mMap.addMarker(MarkerOptions().icon(BitmapDescriptorFactory.fromResource(R.drawable.miniatura)).anchor(0.0f, 1.0f).position(cordinates).title("Fisiokinetics Campeche"))
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(cordinates, 13f), 4000, null)
        //mMap.moveCamera(CameraUpdateFactory.newLatLng(marcador))

        var latitud = 0
        var longitud = 1
        var valor = true
        for(i in lugaresCamp){
            while(valor){
                var cordinates1 = LatLng(cordenadasCam[latitud], cordenadasCam[longitud])
                mMap.addMarker(MarkerOptions().icon(BitmapDescriptorFactory.fromResource(R.drawable.miniatura)).anchor(0.0f, 1.0f).position(cordinates1).title("$i"))
                latitud += 2
                longitud += 2
                valor = false
            }
            valor = true
        }
    }

    private fun createMarkerMerida() {
        // añade marcador
        val cordinates = LatLng(20.996133, -89.6466151)
        mMap.addMarker(MarkerOptions().icon(BitmapDescriptorFactory.fromResource(R.drawable.miniatura)).anchor(0.0f, 1.0f).position(cordinates).title("FISIORIM Fisioterapia Rehabilitación Integral Mérida"))
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(cordinates, 12f), 4000, null)

        var latitud = 0
        var longitud = 1
        var valor = true
        for(i in lugaresMer){
            while(valor){
                var cordinates1 = LatLng(cordenadasMer[latitud], cordenadasMer[longitud])
                mMap.addMarker(MarkerOptions().icon(BitmapDescriptorFactory.fromResource(R.drawable.miniatura)).anchor(0.0f, 1.0f).position(cordinates1).title("$i"))
                latitud += 2
                longitud += 2
                valor = false
            }
            valor = true
        }


    }
    private fun createMarkerCancun() {
        // añade marcador
        val cordinates = LatLng(21.1445788,-86.8245505)
        mMap.addMarker(MarkerOptions().icon(BitmapDescriptorFactory.fromResource(R.drawable.miniatura)).anchor(0.0f, 1.0f).position(cordinates).title("Therapik Fisioterapia y Rehabilitación"))
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(cordinates, 12f), 4000, null)
        //mMap.moveCamera(CameraUpdateFactory.newLatLng(marcador))

        var latitud = 0
        var longitud = 1
        var valor = true
        for(i in lugaresCan){
            while(valor){
                var cordinates1 = LatLng(cordenadasCan[latitud], cordenadasCan[longitud])
                mMap.addMarker(MarkerOptions().icon(BitmapDescriptorFactory.fromResource(R.drawable.miniatura)).anchor(0.0f, 1.0f).position(cordinates1).title("$i"))
                latitud += 2
                longitud += 2
                valor = false
            }
            valor = true
        }
    }
    private fun createMarkerChiapas() {
        // añade marcador
        val cordinates = LatLng(14.8906202, -92.2673321)
        mMap.addMarker(MarkerOptions().icon(BitmapDescriptorFactory.fromResource(R.drawable.miniatura)).anchor(0.0f, 1.0f).position(cordinates).title("CRIS Centro de Rehabilitación Integral del Soconusco"))
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(cordinates, 8f), 4000, null)
        //mMap.moveCamera(CameraUpdateFactory.newLatLng(marcador))

        var latitud = 0
        var longitud = 1
        var valor = true
        for(i in lugaresChia){
            while(valor){
                var cordinates1 = LatLng(cordenadasChia[latitud], cordenadasChia[longitud])
                mMap.addMarker(MarkerOptions().icon(BitmapDescriptorFactory.fromResource(R.drawable.miniatura)).anchor(0.0f, 1.0f).position(cordinates1).title("$i"))
                latitud += 2
                longitud += 2
                valor = false
            }
            valor = true
        }
    }
    private fun createMarkerTabasco() {
        // añade marcador
        val cordinates = LatLng(17.9738421, -92.9573714)
        mMap.addMarker(MarkerOptions().icon(BitmapDescriptorFactory.fromResource(R.drawable.miniatura)).anchor(0.0f, 1.0f).position(cordinates).title("Terafis Consultorio de Fisioterapia"))
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(cordinates, 10f), 4000, null)
        //mMap.moveCamera(CameraUpdateFactory.newLatLng(marcador))

        var latitud = 0
        var longitud = 1
        var valor = true
        for(i in lugaresTabas){
            while(valor){
                var cordinates1 = LatLng(cordenadasTabas[latitud], cordenadasTabas[longitud])
                mMap.addMarker(MarkerOptions().icon(BitmapDescriptorFactory.fromResource(R.drawable.miniatura)).anchor(0.0f, 1.0f).position(cordinates1).title("$i"))
                latitud += 2
                longitud += 2
                valor = false
            }
            valor = true
        }
    }
}