package com.swiftksu.posapp.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.swiftksu.posapp.data.repo.PriceBookRepository
import com.swiftksu.posapp.data.repo.TransactionRepository
import com.swiftksu.posapp.domain.dto.PriceBookItem
import com.swiftksu.posapp.domain.dto.Transaction
import com.swiftksu.posapp.domain.dto.TransactionItem
import com.swiftksu.posapp.domain.dto.TransactionStatus
import com.swiftksu.posapp.domain.usecase.GetCartTransactionValues
import com.swiftksu.posapp.ui.DashboardViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.util.Calendar
import java.util.Date
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(
    private val priceBookRepository: PriceBookRepository,
    private val transactionRepository: TransactionRepository,
    private val getCartTransactionValues: GetCartTransactionValues
) : ViewModel() {

    private val _viewState = MutableStateFlow(DashboardViewState())
    val viewModelState = _viewState.asStateFlow()

    private val _discountPopState = MutableStateFlow(false)
    val discountPopState = _discountPopState.asStateFlow()

    private val _historyPopState = MutableStateFlow(false)
    val historyPopState = _discountPopState.asStateFlow()

    private val _transactionHistory = MutableStateFlow<List<Transaction>>(emptyList())
    val transactionHistory = _transactionHistory.asStateFlow()

    private lateinit var trxnStartTime: Date

    private val TAG: String = this.toString()

    // Initialize the ViewModel
    init {
        viewModelScope.launch {
            _viewState.value =
                _viewState.value.copy(priceBookItems = priceBookRepository.getPriceBookItems())
        }
    }

    fun onPriceBookItemClicked(item: PriceBookItem) {
        // Handle tapping on a PriceBook item
        // Add to the cart or increment quantity
        viewModelScope.launch(Dispatchers.Default) {
            if (_viewState.value.transactionItems.isEmpty()) {
                trxnStartTime = Calendar.getInstance().time
            }
            val transactionItems = _viewState.value.transactionItems
            var hasItemMatch = false
            for (tItem: TransactionItem in transactionItems) {
                if (tItem.pluItem.equals(item)) {
                    tItem.quantity++
                    hasItemMatch = true
                    break
                }
            }
            if (!hasItemMatch) {
                transactionItems.add(TransactionItem(pluItem = item, quantity = 1))
            }
            _viewState.update {
                val sT = it.subTotal + item.price
                var tT = 0f
                for (tax: Float in item.taxRates) {
                    tT += ((item.price * tax) / 100f)
                }
                tT += it.totalTax
                val gT = sT + tT - 0f
                it.copy(
                    transactionItems = transactionItems,
                    subTotal = sT,
                    totalTax = tT,
                    grandTotal = gT
                )
            }
            getCartTransactionValues.setTransactionList(transactionItems)
        }
    }

    fun onSaveClicked() {
        Log.d(TAG, "onSaveClicked")
        viewModelScope.launch(Dispatchers.IO) {
            val transactionItems = _viewState.value.transactionItems
            val transaction = Transaction(
                txnId = transactionItems.hashCode().toString(),
                txnItems = transactionItems,
                txnEndTime = Calendar.getInstance().time,
                txnTotalGrandAmount = _viewState.value.grandTotal,
                txnTotalDiscountAmount = _viewState.value.discount,
                txnTotalTaxAmount = _viewState.value.totalTax,
                txnSubTotalAmount = _viewState.value.subTotal,
                txnStatus = TransactionStatus.SAVED,
                txnStartTime = trxnStartTime,
            )
            transactionRepository.deleteEarlierSavedTransactions()
            transactionRepository.saveTransaction(transaction)
        }
    }

    fun onRecallClicked() {
        Log.d(TAG, "onRecallClicked")
        viewModelScope.launch(Dispatchers.IO) {
            val transaction = transactionRepository.recallLastSavedTransaction()
            transaction?.let { trxn ->
                _viewState.update {
                    it.copy(
                        transactionItems = trxn.txnItems.toMutableList(),
                        subTotal = trxn.txnSubTotalAmount,
                        totalTax = trxn.txnTotalTaxAmount,
                        discount = trxn.txnTotalDiscountAmount,
                        grandTotal = trxn.txnTotalGrandAmount
                    )
                }
            }
        }
    }

    fun onCompleteClicked() {
        Log.d(TAG, "onCompleteClicked")
        viewModelScope.launch(Dispatchers.IO) {
            val transactionItems = _viewState.value.transactionItems
            val transaction = Transaction(
                txnId = transactionItems.hashCode().toString(),
                txnItems = transactionItems,
                txnEndTime = Calendar.getInstance().time,
                txnTotalGrandAmount = _viewState.value.grandTotal,
                txnTotalDiscountAmount = _viewState.value.discount,
                txnTotalTaxAmount = _viewState.value.totalTax,
                txnSubTotalAmount = _viewState.value.subTotal,
                txnStatus = TransactionStatus.SAVED,
                txnStartTime = trxnStartTime,
            )
            transactionRepository.addCompletedTransaction(transaction)
            _viewState.value = DashboardViewState(priceBookItems = _viewState.value.priceBookItems)
        }
    }

    fun onHistoryClicked() {
        Log.d(TAG, "onHistoryClicked")
        _historyPopState.update {
            true
        }
        viewModelScope.launch {
            _transactionHistory.value = transactionRepository.getAllTransactionsHistory()
        }
    }

    fun onDiscountClicked() {
        Log.d(TAG, "onDiscountClicked")
        _discountPopState.update {
            true
        }
    }

    fun onApplyDiscountClicked(discountPer: Float = 0f, disAmount: Float = 0f) {
        Log.d(TAG, "onApplyDiscountClicked : $discountPer, $disAmount")
        _discountPopState.update {
            false
        }
        if(disAmount > 0) {
            _viewState.value.discount = disAmount
        } else {
            _viewState.value.discount = (_viewState.value.subTotal * discountPer) / 100
        }
    }

    /*fun onCartItemClicked(transactionItem: TransactionItem) {
    }*/
}
