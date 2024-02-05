package com.swiftksu.posapp.data.repo

import com.swiftksu.posapp.domain.dto.PriceBookItem

interface PriceBookRepository {
    suspend fun getPriceBookItems(): List<PriceBookItem>
}