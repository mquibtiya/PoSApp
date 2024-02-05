package com.swiftksu.posapp.ui.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.input.KeyboardType

private var discountPer: Float = 0.0f
private var discountAmount: Float = 0.0f

@Composable
fun AddDiscountUi(onApplyDiscountClick: (discountPer: Float, disAmount: Float) -> Unit) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        TextInput(label = "Discount Percentage", 1)
        TextInput(label = "Discount Amount", 2)

        //Hardcoded discountPer & discountAmount to verify keyboard input behaviour
        Button(onClick = { onApplyDiscountClick(discountPer, discountAmount) }) {
            Text(text = "Apply")
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TextInput(label: String, id: Int) {
    val state = remember {
        mutableStateOf("0")
    }
    TextField(
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        value = state.value,
        onValueChange = {
            if (id == 1) {
                discountPer = it.toFloat()
            } else {
                discountAmount = it.toFloat()
            }
            state.value = it
        }, label = { Text(text = label) })
}