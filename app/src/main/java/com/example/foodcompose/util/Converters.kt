package com.example.foodcompose.util

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

@ProvidedTypeConverter
class Converters constructor(private val gson: Gson)  {
    @TypeConverter
    fun fromString(items: List<String>?): String? {
        if(items == null) return null

        return gson.toJson(items)
    }

    @TypeConverter
    fun toString(items: String?): List<String> {
        if(items == null) return emptyList()

        val listType = object : TypeToken<List<String>>() {}.type

        return gson.fromJson(items, listType)
    }
}