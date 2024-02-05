//package com.swiftksu.posapp.ui.composables
//
//import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.fillMaxSize
//import androidx.compose.material3.MaterialTheme
//import androidx.compose.material3.Surface
//import androidx.compose.runtime.*
//import androidx.compose.ui.Modifier
//import androidx.hilt.navigation.compose.hiltViewModel
//import androidx.lifecycle.ViewModel
//import androidx.lifecycle.ViewModelProvider
//import androidx.lifecycle.viewmodel.compose.viewModel
//import com.swiftksu.posapp.domain.dto.PriceBookItem
//import com.swiftksu.posapp.domain.dto.TransactionItem
//import com.swiftksu.posapp.data.repo.PriceBookRepository
//import com.swiftksu.posapp.data.repo.TransactionRepository
//import com.swiftksu.posapp.presentation.viewmodel.DashboardViewModel
//import com.swiftksu.posapp.ui.theme.PoSAppTheme
//
//@Composable
//fun DashboardScreen(viewModel: DashboardViewModel = hiltViewModel()) {
//    val state by viewModel.viewModelState.collectAsState()
//
//    PoSAppTheme {
//        // A surface container using the 'background' color from the theme
//        Surface(
//            modifier = Modifier.fillMaxSize(),
//            color = MaterialTheme.colorScheme.background
//        ) {
//            Column {
//                // Cart Section
//                CartSection(
////                    cartItems = state.cartItems,
////                    onItemClicked = viewModel::onCartItemClicked
//                )
//
//                // PriceBookItems Grid
//                PriceBookGrid(
//                    priceBookItems = state.priceBookItems,
//                    onItemClick = viewModel::onPriceBookItemClicked
//                )
//
//                // Cart Footer
//                CartFooter(
//                    subTotal = state.subTotal,
//                    totalTax = state.totalTax,
//                    discount = state.discount,
//                    grandTotal = state.grandTotal
//                )
//
//                // CTAs
//                DashboardActions(
//                    onSaveClicked = viewModel::onSaveClicked,
//                    onRecallClicked = viewModel::onRecallClicked,
//                    onCompleteClicked = viewModel::onCompleteClicked,
//                    onHistoryClicked = viewModel::onHistoryClicked,
//                    onDiscountClicked = viewModel::onDiscountClicked
//                )
//            }
//        }
//    }
//}
//
//@Composable
//fun CartSection(cartItems: List<TransactionItem>, onItemClicked: (TransactionItem) -> Unit) {
//    // Implement the UI for displaying Cart items
//}
//
//@Composable
//fun PriceBookGrid(priceBookItems: List<PriceBookItem>, onItemClick: (PriceBookItem) -> Unit) {
//    // Implement the UI for displaying PriceBook items in a grid
//}
///*
//
//@Composable
//fun ProvideViewModel(
//    priceBookRepository: PriceBookRepository,
//    transactionRepository: TransactionRepository,
//    content: @Composable (DashboardViewModel) -> Unit
//) {
//    val viewModel: DashboardViewModel = viewModel(
//        factory = DashboardViewModelFactory(priceBookRepository, transactionRepository)
//    )
//    content(viewModel)
//}
//
//
//class DashboardViewModelFactory(
//    private val priceBookRepository: PriceBookRepository,
//    private val transactionRepository: TransactionRepository
//) : ViewModelProvider.Factory {
//    override fun <T : ViewModel> create(modelClass: Class<T>): T {
//        if (modelClass.isAssignableFrom(DashboardViewModel::class.java)) {
//            @Suppress("UNCHECKED_CAST")
//            return DashboardViewModel(*/
///*priceBookRepository, transactionRepository*//*
//) as T
//        }
//        throw IllegalArgumentException("Unknown ViewModel class")
//    }
//}
//
//@Composable
//fun DashboardScreen(
//    priceBookRepository: PriceBookRepository,
//    transactionRepository: TransactionRepository
//) {
//    ProvideViewModel(priceBookRepository, transactionRepository) { viewModel ->
//        DashboardScreen(viewModel)
//    }
//}
//*/
