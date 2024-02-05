package com.swiftksu.posapp.ui.composables

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.window.Popup

@Composable
fun DiscountPopupUi(
    popupControl: Boolean,
    onApplyDiscountClick: (discountPer: Float, disAmount: Float) -> Unit
) {
    if (popupControl) {
        Popup(offset = IntOffset(100, 100)) {
            // Composable content to be shown in the Popup
            AddDiscountUi(onApplyDiscountClick)
        }
    }
}