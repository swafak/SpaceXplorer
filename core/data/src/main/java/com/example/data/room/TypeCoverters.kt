package com.example.data.room

import androidx.room.TypeConverter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types

object Converters {
    private val moshi = Moshi.Builder().build()
    private val listType = Types.newParameterizedType(List::class.java, String::class.java)
    private val adapter = moshi.adapter<List<String>>(listType)

    @TypeConverter
    fun fromList(list: List<String>?): String {
        return adapter.toJson(list ?: emptyList())  // Convert List to JSON String
    }

    @TypeConverter
    fun toList(json: String): List<String> {
        return adapter.fromJson(json) ?: emptyList()  // Convert JSON String back to List
    }
}
