package com.swiftksu.posapp.ui.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.window.Popup
import com.swiftksu.posapp.domain.dto.Transaction

@Composable
fun TransactionHistoryUi(transactionList: List<Transaction>) {
    Popup {
        LazyColumn {
            items(transactionList) {
                TransactionItemUi(transactionItem = it)
            }
        }
    }
}

@Composable
fun TransactionItemUi(transactionItem: Transaction) {
    Row(horizontalArrangement = Arrangement.SpaceBetween) {
        Text(text = transactionItem.txnId)
        Text(text = transactionItem.txnItems.sumOf { it.quantity }.toString())
        Text(text = transactionItem.txnTotalGrandAmount.toString())
        Text(text = transactionItem.txnEndTime.toString())

    }
}
/*
* val txnId: String,
    val txnItems: List<TransactionItem>,
    val txnEndTime: Date,
    val txnTotalGrandAmount: Float,
    val txnTotalDiscountAmount: Float,
    val txnTotalTaxAmount: Float,
    val txnSubTotalAmount: Float,
    val txnStatus: TransactionStatus, // COMPLETED, SAVED
    val txnStartTime: Date,
* */