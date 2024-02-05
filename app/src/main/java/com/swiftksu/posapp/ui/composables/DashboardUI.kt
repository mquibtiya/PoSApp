package com.swiftksu.posapp.ui.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.swiftksu.posapp.presentation.viewmodel.DashboardViewModel
import com.swiftksu.posapp.ui.dto.CartFooterItems

@Preview
@Composable
fun DashboardUI(viewModel: DashboardViewModel = hiltViewModel()) {

    val state by viewModel.viewModelState.collectAsState()
    val popUpState by viewModel.discountPopState.collectAsState()
    val transactionHistoryState by viewModel.historyPopState.collectAsState()
    val transactionHistoryList by viewModel.transactionHistory.collectAsState()

    Box {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Row(horizontalArrangement = Arrangement.SpaceEvenly, modifier = Modifier.weight(1f)) {
                Column(modifier = Modifier.weight(0.5f)) {
                    CartSection(
                        state.transactionItems,
                        CartFooterItems(
                            state.subTotal,
                            state.totalTax,
                            state.discount,
                            state.grandTotal
                        )
                    )
                }
                Column(modifier = Modifier.weight(0.5f)) {
                    GridSectionItemsList(
                        numOfCols = 2,
                        itemsList = state.priceBookItems.toTypedArray(),
                        viewModel::onPriceBookItemClicked
                    )
                }
            }
            DashboardActions(
                onSaveClicked = viewModel::onSaveClicked,
                onRecallClicked = viewModel::onRecallClicked,
                onCompleteClicked = viewModel::onCompleteClicked,
                onHistoryClicked = viewModel::onHistoryClicked,
                onDiscountClicked = viewModel::onDiscountClicked
            )
        }
        if (popUpState) {
            DiscountPopupUi(popupControl = popUpState, onApplyDiscountClick = viewModel::onApplyDiscountClicked)
        }
        if (transactionHistoryState && !transactionHistoryList.isEmpty()) {
            TransactionHistoryUi(transactionList = transactionHistoryList)
        }
    }
}