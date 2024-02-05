package com.swiftksu.posapp.data.roomdb.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.swiftksu.posapp.data.roomdb.DataConverter
import com.swiftksu.posapp.domain.dto.Transaction
import com.swiftksu.posapp.domain.dto.TransactionItem
import com.swiftksu.posapp.domain.dto.TransactionStatus
import java.util.Date

@Entity(tableName = TransactionEntity.TABLE_NAME)
data class TransactionEntity(
    @PrimaryKey
    val txnId: String,
    val txnItems: List<TransactionItem>,
    val txnEndTime: Date,
    val txnTotalGrandAmount: Float,
    val txnTotalDiscountAmount: Float,
    val txnTotalTaxAmount: Float,
    val txnSubTotalAmount: Float,
    val txnStatus: TransactionStatus, // COMPLETED, SAVED
    val txnStartTime: Date,
) {
    fun toTransactionItemDto(): Transaction {
        return Transaction(
            txnId = txnId,
            txnItems = txnItems,
            txnEndTime = txnEndTime,
            txnTotalGrandAmount = txnTotalGrandAmount,
            txnTotalDiscountAmount = txnTotalDiscountAmount,
            txnTotalTaxAmount = txnTotalTaxAmount,
            txnSubTotalAmount = txnSubTotalAmount,
            txnStatus = txnStatus,
            txnStartTime = txnStartTime
        )
    }

    companion object {
        const val TABLE_NAME: String = "transactions"
    }
}