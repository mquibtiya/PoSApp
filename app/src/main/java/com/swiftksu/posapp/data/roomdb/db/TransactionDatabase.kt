package com.swiftksu.posapp.data.roomdb.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.mariya.flipkarttest.data.source.local.roomdb.dao.TransactionDao
import com.swiftksu.posapp.data.roomdb.DataConverter
import com.swiftksu.posapp.data.roomdb.entity.TransactionEntity
import com.swiftksu.posapp.data.roomdb.entity.TransactionItemEntity

@Database(entities = [TransactionEntity::class, TransactionItemEntity::class], version = 1, exportSchema = false)
@TypeConverters(DataConverter::class, )
abstract class TransactionDatabase : RoomDatabase() {
    abstract fun transactionDao(): TransactionDao
}
