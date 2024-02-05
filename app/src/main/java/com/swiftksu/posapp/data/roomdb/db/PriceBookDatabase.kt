package com.swiftksu.posapp.data.roomdb.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.swiftksu.posapp.data.roomdb.dao.PriceBookItemDao
import com.swiftksu.posapp.data.roomdb.entity.PriceBookItemEntity

@Database(entities = [PriceBookItemEntity::class], version = 1, exportSchema = false)
abstract class PriceBookDatabase : RoomDatabase() {
    abstract fun priceBookItemDao(): PriceBookItemDao
}