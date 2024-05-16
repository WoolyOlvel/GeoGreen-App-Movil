package com.example.dinospizza

import android.os.StrictMode
import android.telecom.Connection
import java.sql.DriverManager

class Connect {
       //private val ip = "192.168.56.1:59655"
    private val ip = "192.168.1.105:59655"
       //private val db = "DinosPizza"
    private val db = "Pizzeria"
    private val username = "admin"
    private val password = "admin"

    fun dbConn(): java.sql.Connection?{
        val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
        StrictMode.setThreadPolicy(policy)
        var conn : java.sql.Connection? = null
        val connString : String
        try {
            Class.forName("net.sourceforge.jtds.jdbc.Driver").newInstance()
            connString = "jdbc:jtds:sqlserver://$ip;databaseName=$db;user=$username;password=$password"
            conn = DriverManager.getConnection(connString)
        }catch (ex : Exception){
            ex.printStackTrace()

        }
        return  conn

    }
}