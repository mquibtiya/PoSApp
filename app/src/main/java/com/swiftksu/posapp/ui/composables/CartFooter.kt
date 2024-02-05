package com.swiftksu.posapp.ui.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.swiftksu.posapp.ui.dto.CartFooterItems

@Composable
fun CartFooter(cartFooterItems: CartFooterItems) {
    Column(
        modifier = Modifier
            .padding(8.dp),
        verticalArrangement = Arrangement.Bottom
    ) {
        TransactionRow(heading = "Sub Total", value = "${cartFooterItems.subTotal}")
        TransactionRow(heading = "Total Tax", value = "${cartFooterItems.totalTax}")
        TransactionRow(heading = "Discount", value = "${cartFooterItems.discount}")
        TransactionRow(heading = "Grand Total", value = "${cartFooterItems.grandTotal}")
    }
}

@Composable
fun TransactionRow(heading: String, value: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth(Float.MAX_VALUE)
    ) {
        Text(text = heading, Modifier.weight(0.5f))
        Text(text = value, Modifier.weight(0.5f))
    }
}