package com.swiftksu.posapp.data.roomdb.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "price_book_items")
data class PriceBookItemEntity(
    @PrimaryKey
    val pluId: String,
    val itemName: String,
    val price: Float,
)