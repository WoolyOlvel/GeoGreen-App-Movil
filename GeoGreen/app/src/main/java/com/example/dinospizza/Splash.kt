package com.example.dinospizza

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class Splash : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        Thread.sleep(2000)
        if(testEstadoSesion()){
            startActivity(Intent(applicationContext, MainActivity::class.java))
            finish()
        }
        else{
            startActivity(Intent(applicationContext, Login::class.java))
            finish()
        }

    }
    fun testEstadoSesion():Boolean{
        val pref: SharedPreferences = getSharedPreferences(
            sharePreferenceSesion, Context.MODE_PRIVATE
        )
        return pref.getBoolean(testSesion,false)
    }
}