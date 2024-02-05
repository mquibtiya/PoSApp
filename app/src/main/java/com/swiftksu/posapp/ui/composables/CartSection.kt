package com.swiftksu.posapp.ui.composables

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.swiftksu.posapp.domain.dto.TransactionItem
import com.swiftksu.posapp.ui.dto.CartFooterItems

@Composable
fun CartSection(transactionItems: List<TransactionItem> = emptyList(), cartFooterItems: CartFooterItems = CartFooterItems()) {
    Column(verticalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier.fillMaxHeight()) {
        CartItemsList(transactionItems = transactionItems)
        CartFooter(cartFooterItems)
    }
}

@Composable
fun CartItemsList(transactionItems: List<TransactionItem>) {
    LazyColumn(verticalArrangement = Arrangement.Top) {
        items(transactionItems) {
            CartItem(transactionItem = it)
        }
    }
}