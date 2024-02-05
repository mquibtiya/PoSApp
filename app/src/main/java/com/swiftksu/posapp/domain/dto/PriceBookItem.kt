package com.swiftksu.posapp.domain.dto

data class PriceBookItem(
    val pluId: String,
    val itemName: String,
    val taxRates: List<Float>, // 5.5%, 6.0%
    val price: Float
)
