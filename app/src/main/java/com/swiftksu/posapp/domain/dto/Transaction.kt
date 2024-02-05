package com.swiftksu.posapp.domain.dto

import java.util.Date

data class Transaction(
    val txnId: String,
    val txnItems: List<TransactionItem>,
    val txnEndTime: Date,
    val txnTotalGrandAmount: Float,
    val txnTotalDiscountAmount: Float,
    val txnTotalTaxAmount: Float,
    val txnSubTotalAmount: Float,
    val txnStatus: TransactionStatus, // COMPLETED, SAVED
    val txnStartTime: Date,
)

enum class TransactionStatus {
    COMPLETED,
    SAVED
}
