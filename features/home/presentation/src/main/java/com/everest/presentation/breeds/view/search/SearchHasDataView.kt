package com.everest.presentation.breeds.view.search

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.everest.domain.model.categories.breed.BreedVo

@Composable
fun SearchHasDataView(
    modifier: Modifier = Modifier,
    categories: List<BreedVo>
) {
    LazyColumn(modifier = modifier) {
        items(count = categories.size, key = { index -> categories[index].id }) { index ->
            val currentVo = categories[index]
            Text(
                text = currentVo.name,
                modifier = modifier.padding(
                    horizontal = 16.dp,
                    vertical = 8.dp
                )
            )
        }
    }
}
