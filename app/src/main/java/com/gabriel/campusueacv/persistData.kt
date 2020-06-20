package com.gabriel.campusueacv

import android.content.Context

class persistData(context: Context) {

    private var PREF_INIT = "MyFile"
    private var PREF_NAME = "name"

    val preference = context.getSharedPreferences(PREF_INIT,Context.MODE_PRIVATE)

    fun getNameData() : String{
        return preference.getString(PREF_NAME, "Bem vindo").toString()
    }

    fun setDataName(name: String){
        val editor = preference.edit()
        editor.putString(PREF_NAME,name)

        editor.apply()
    }

}