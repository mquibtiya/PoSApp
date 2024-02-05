package com.swiftksu.posapp.ui.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun GetButton(onClick: () -> Unit, modifier: Modifier = Modifier, text: String) {
    Button(onClick = { onClick() }, modifier = modifier) {
        Text(text = text, maxLines = 1)
    }
}

@Composable
fun DashboardActions(
    onSaveClicked: () -> Unit,
    onRecallClicked: () -> Unit,
    onCompleteClicked: () -> Unit,
    onHistoryClicked: () -> Unit,
    onDiscountClicked: () -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.Bottom
    ) {
        GetButton(onClick = { onSaveClicked() }, text = "Save")
        GetButton(onClick = { onRecallClicked() }, text = "Recall")
        GetButton(onClick = { onCompleteClicked() }, text = "Complete")
        GetButton(onClick = { onHistoryClicked() }, text = "History")
        GetButton(onClick = { onDiscountClicked() }, text = "Discount")
    }
}