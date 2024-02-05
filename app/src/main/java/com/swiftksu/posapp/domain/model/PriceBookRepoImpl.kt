package com.swiftksu.posapp.domain.model

import android.content.Context
import com.google.gson.Gson
import com.swiftksu.posapp.data.repo.PriceBookRepository
import com.swiftksu.posapp.domain.dto.ItemsFromJson
import com.swiftksu.posapp.domain.dto.PriceBookItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.IOException
import javax.inject.Inject

/*
class PriceBookRepositoryImpl @Inject constructor(
    private val priceBookItemDao: PriceBookItemDao
) : PriceBookRepository {

    override suspend fun getPriceBookItems(): List<PriceBookItem> {
        val entities = priceBookItemDao.getPriceBookItems()
        return entities.map { it.toDomainModel() }
    }

    private fun PriceBookItemEntity.toDomainModel(): PriceBookItem {
        return PriceBookItem(pluId, itemName, emptyList(), price)
    }
}
*/

class PriceBookRepoImpl(private val context: Context) : PriceBookRepository {

    override suspend fun getPriceBookItems(): List<PriceBookItem> {
        return try {
            withContext(Dispatchers.IO) {
                val inputStream = context.assets.open("test_data.json")
                val size : Int = inputStream.available()
                val buffer = ByteArray(size)
                inputStream.read(buffer)
                inputStream.close()
                val json = String(buffer, Charsets.UTF_8)
                val gson = Gson()
                gson.fromJson(json, ItemsFromJson::class.java).pricebook.toList()
            }
        } catch (e: IOException) {
            emptyList()
        }
    }
}
