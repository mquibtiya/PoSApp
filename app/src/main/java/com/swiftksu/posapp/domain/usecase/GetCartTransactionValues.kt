package com.swiftksu.posapp.domain.usecase

import com.swiftksu.posapp.domain.dto.TransactionItem
import javax.inject.Inject

class GetCartTransactionValues @Inject constructor() {

    private var transactionItems: MutableList<TransactionItem> = mutableListOf()

    fun addTransaction(item: TransactionItem) {
        transactionItems.add(item)
    }

    fun setTransactionList(list: MutableList<TransactionItem>) {
        transactionItems = list
    }

    fun getSubTotal(): Float {
        var subTotal = 0f
        for (item: TransactionItem in transactionItems) {
            subTotal += (item.quantity * item.pluItem.price)
        }
        return subTotal
    }

    fun getTotalTax(): Float {
        var totalTax = 0f
        var taxForItem: Float
        for (item: TransactionItem in transactionItems) {
            taxForItem = 0f
            for (tax: Float in item.pluItem.taxRates) {
                taxForItem += item.pluItem.price * tax
            }
            totalTax += taxForItem * item.quantity
        }
        return totalTax
    }

    private fun getTotalDiscount(
        discountPercentage: Float = 0f,
        discountAmount: Float = 0f,
        shouldApplyPercentage: Boolean = false
    ): Float = if (shouldApplyPercentage) {
        getSubTotal() * discountPercentage
    } else {
        discountAmount
    }

    fun getGrandTotal(): Float = getSubTotal() + getTotalTax() - getTotalDiscount()
}