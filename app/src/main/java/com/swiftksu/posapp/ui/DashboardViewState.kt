package com.swiftksu.posapp.ui

import com.swiftksu.posapp.domain.dto.PriceBookItem
import com.swiftksu.posapp.domain.dto.TransactionItem

data class DashboardViewState(
    val priceBookItems: List<PriceBookItem> = emptyList(),
    val transactionItems: MutableList<TransactionItem> = mutableListOf(),
    var subTotal: Float = 0f,
    var totalTax: Float = 0f,
    var discount: Float = 0f,
    var grandTotal: Float = 0f
)

fun DashboardViewState.clearTransaction() : DashboardViewState {
    transactionItems.clear()
    subTotal = 0f
    totalTax = 0f
    discount = 0f
    grandTotal = 0f
    return this
}
