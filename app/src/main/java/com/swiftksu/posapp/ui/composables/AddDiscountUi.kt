package com.swiftksu.posapp.ui.composables

import android.util.Log
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
import androidx.core.text.isDigitsOnly

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
        mutableStateOf("")
    }
    TextField(
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        value = state.value,
        onValueChange = { textInput ->
            Log.d("Mariya", "onValueChange : $textInput")
            if (!(textInput.isDigitsOnly() || textInput.contains("."))) return@TextField
            state.value = textInput
            if (id == 1) {
                discountPer = textInput.toFloat()
            } else {
                discountAmount = textInput.toFloat()
            }
        },
        label = {
            Text(text = label)
        }
    )
}