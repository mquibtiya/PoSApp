package com.swiftksu.posapp.ui.composables

import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.window.Popup
import androidx.compose.ui.window.PopupProperties

@Composable
fun DiscountPopupUi(
    popupControl: Boolean,
    onApplyDiscountClick: (discountPer: Float, disAmount: Float) -> Unit
) {
    if (popupControl) {
        Popup(
            alignment = Alignment.Center,
            properties = PopupProperties(focusable = true)
        ) {
            // Composable content to be shown in the Popup
            AddDiscountUi(onApplyDiscountClick)
        }
    }
}