package com.example.dinospizza

import android.app.Application
import android.content.Context
import java.net.InetAddress

class App: Application() {

    companion object{
        lateinit var context: Context

        var incre : Int = 0
        var incre2 : Int = 0
        var incremap : Int = 0
        var incre3 : Int = 0
        var ip = "192.168.1.114"
        var titleG = mutableListOf<String>()
        var title2 = mutableListOf<String>()
        var subtitle2 = mutableListOf<String>()
        var title3 = mutableListOf<String>()
        var subtitleG = mutableListOf<String>()
        var image2 = mutableListOf<Int>()
        var cos2 = mutableListOf<String>()
        var cos3 = mutableListOf<String>()



        var Oftitle = mutableListOf<String>()
        var Oftitle2 = mutableListOf<String>()
        var Ofsubtitle = mutableListOf<String>()
        var Ofimagencad = mutableListOf<Int>()
        var Ofcos = mutableListOf<String>()
        var Ofcali = mutableListOf<String>()
        var Oflim = mutableListOf<String>()
        var Ofdes = mutableListOf<String>()

    }

    override fun onCreate() {
        super.onCreate()
        context = applicationContext
        ip = "192.168.1.114"

    }
}