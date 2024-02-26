package com.everest.presentation.breeds.view.search

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun SearchHistoryView(
    modifier: Modifier = Modifier,
    histories: List<String>
) {
    LazyColumn(modifier = modifier) {
        items(count = histories.size, key = { index -> histories[index] }) { index ->
            val currentVo = histories[index]
            Text(text = currentVo, modifier = modifier.padding(8.dp))
        }
    }
}
