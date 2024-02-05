package com.swiftksu.posapp.data

import android.content.Context
import androidx.compose.runtime.mutableStateOf
import com.google.gson.Gson
import com.swiftksu.posapp.domain.dto.ItemsFromJson
import com.swiftksu.posapp.domain.dto.PriceBookItem

object DataManager {
    var priceItemList = emptyArray<PriceBookItem>()
    var isDataLoaded = mutableStateOf(false)

    fun loadAssetsFromFile(context: Context) {
        val inputStream = context.assets.open("test_data.json")
        val size : Int = inputStream.available()
        val buffer = ByteArray(size)
        inputStream.read(buffer)
        inputStream.close()
        val json = String(buffer, Charsets.UTF_8)
        val gson = Gson()
        priceItemList = gson.fromJson(json, ItemsFromJson::class.java).pricebook
        isDataLoaded.value = true
    }
}