package com.swiftksu.posapp.data.roomdb

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.swiftksu.posapp.domain.dto.TransactionItem
import java.lang.reflect.Type
import java.util.Date


class DataConverter {
    @TypeConverter
    fun fromCountryLangList(countryLang: List<TransactionItem?>?): String? {
        if (countryLang == null) {
            return null
        }
        val gson = Gson()
        val type: Type = object :
            TypeToken<List<TransactionItem?>?>() {}.type
        return gson.toJson(countryLang, type)
    }

    @TypeConverter
    fun toCountryLangList(countryLangString: String?): List<TransactionItem?>? {
        if (countryLangString == null) {
            return null
        }
        val gson = Gson()
        val type: Type = object :
            TypeToken<List<TransactionItem?>?>() {}.type
        return gson.fromJson<List<TransactionItem>>(countryLangString, type)
    }

    @TypeConverter
    fun toDate(timestamp: Long?): Date? {
        return timestamp?.let { Date(it) }
    }

    @TypeConverter
    fun toTimestamp(date: Date?): Long? {
        return date?.time
    }
}