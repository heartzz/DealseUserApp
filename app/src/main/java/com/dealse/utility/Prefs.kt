package com.dealse.utility

import android.content.Context
import android.content.SharedPreferences

import com.google.gson.Gson

object Prefs {

    private var sharedPreferences: SharedPreferences? = null

    private fun openPrefs(context: Context) {
        sharedPreferences = context.getSharedPreferences("storek", Context.MODE_PRIVATE)
    }

    fun setValue(context: Context, key: String, value: Any) {
        openPrefs(context)
        val prefsEdit: SharedPreferences.Editor? = sharedPreferences!!.edit()

        when (value) {
            is String -> prefsEdit!!.putString(key, value)
            is Int -> prefsEdit!!.putInt(key, value)
            is Boolean -> prefsEdit!!.putBoolean(key, value)
            is Long -> prefsEdit!!.putLong(key, value)
            is Float -> prefsEdit!!.putFloat(key, value)
            else -> prefsEdit!!.putString(key, Gson().toJson(value))
        }
        prefsEdit.apply()
        sharedPreferences = null
    }



    fun getValue(context: Context, key: String, defaultValue: String): String? {
        openPrefs(context)
        val result = sharedPreferences!!.getString(key, defaultValue)
        sharedPreferences = null
        if (key == AppConstant.LAT || key == AppConstant.LONG) {
            if (result == "") {
                return "0.0"
            }
        }
        return result
    }

    fun getValue(context: Context, key: String, defaultValue: Int): Int {
        openPrefs(context)
        val result = sharedPreferences!!.getInt(key, defaultValue)
        sharedPreferences = null
        return result
    }

    fun getValue(context: Context, key: String, defaultValue: Boolean): Boolean {
        openPrefs(context)
        val result = sharedPreferences!!.getBoolean(key, defaultValue)
        sharedPreferences = null
        return result
    }


    fun getObject(context: Context, key: String, defaultValue: String, va: Class<*>): Any {
        val gson = Gson()
        openPrefs(context)
        val result = sharedPreferences!!.getString(key, defaultValue)
        return gson.fromJson(result, va)
    }

    fun removeValue(context: Context, key: String) {
        openPrefs(context)
        val editor = sharedPreferences!!.edit()
        editor.remove(key)
        editor.apply()
    }

    fun clearAllData(context: Context) {
        openPrefs(context)
        val editor = sharedPreferences!!.edit()
        editor.clear()
        editor.apply()
    }

    fun contain(context: Context, key: String): Boolean {
        openPrefs(context)
        return sharedPreferences!!.contains(key)
    }




}

