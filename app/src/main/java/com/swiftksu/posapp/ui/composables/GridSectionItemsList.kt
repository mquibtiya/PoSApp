package com.swiftksu.posapp.ui.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import com.swiftksu.posapp.domain.dto.PriceBookItem

@Composable
fun GridSectionItemsList(numOfCols: Int = 2, itemsList: Array<PriceBookItem>, onClick: (item: PriceBookItem) -> Unit) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(numOfCols),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(itemsList) {
            GridItemUi(item = it, onClick)
        }
    }
}