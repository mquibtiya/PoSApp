package com.swiftksu.posapp.ui.composables

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.swiftksu.posapp.domain.dto.PriceBookItem
import com.swiftksu.posapp.domain.dto.TransactionItem

@Composable
fun CartItem(transactionItem: TransactionItem) {
    Row(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth(Float.MAX_VALUE)
    ) {
        Text(text = transactionItem.pluItem.itemName, Modifier.weight(0.6f))
        Text(text = transactionItem.quantity.toString(), Modifier.weight(0.2f))
        Text(text = transactionItem.pluItem.price.toString(), Modifier.weight(0.2f))
    }
}