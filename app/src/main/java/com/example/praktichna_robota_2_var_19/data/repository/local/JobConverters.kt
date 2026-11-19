package com.example.praktichna_robota_2_var_19.data.repository.local

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class JobConverters {

    private val gson = Gson()

    private val listType =
        object : TypeToken<List<String>>() {}.type

    @TypeConverter
    fun fromStringList(value: List<String>): String {
        return gson.toJson(value)
    }

    @TypeConverter
    fun toStringList(value: String): List<String> {
        return gson.fromJson<List<String>>(
            value,
            listType
        ) ?: emptyList()
    }
}