package com.example.dinospizza

import java.util.regex.Pattern

object Reglas {
    fun Validarnom(Nombre : String) : Int{
        var resul = 1

        Nombre.trimStart()
        Nombre.trimEnd()

        if(Nombre.isBlank()){
            resul = 2
        }
        if (Nombre.length < 3){
            resul = 2
        }
        return resul
    }

    fun valiPas(pass : String) : Int{
        val str = pass
        var valid = 1

        pass.trimStart()
        pass.trimEnd()

        if(pass.isBlank()){
            valid = 2
        }
        if(str.length < 8){
            valid = 2
        }

        var exp = ".*[0-9].*"
        var pattern = Pattern.compile(exp, Pattern.CASE_INSENSITIVE)
        var matcher = pattern.matcher(str)
        if(!matcher.matches()){
            valid = 2
        }

        exp = ".*[A-Z].*"
        pattern = Pattern.compile(exp)
        matcher = pattern.matcher(str)
        if(!matcher.matches()){
            valid = 2
        }

        exp = ".*[a-z].*"
        pattern = Pattern.compile(exp)
        matcher = pattern.matcher(str)
        if(!matcher.matches()){
            valid = 2
        }

        return valid

    }
}