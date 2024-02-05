package com.swiftksu.posapp.data.roomdb.dao

import androidx.room.Dao
import androidx.room.Query
import com.swiftksu.posapp.data.roomdb.entity.PriceBookItemEntity

@Dao
interface PriceBookItemDao {
    @Query("SELECT * FROM price_book_items")
    suspend fun getPriceBookItems(): List<PriceBookItemEntity>
}