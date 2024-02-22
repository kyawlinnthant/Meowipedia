package com.everest.presentation.categories.view.list

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.everest.domain.model.categories.CategoryVO
import com.everest.presentation.categories.CategoriesAction

@Composable
fun ListHasDataView(
    modifier: Modifier = Modifier,
    categories: List<CategoryVO>,
    onAction: (CategoriesAction) -> Unit
) {
    LazyColumn(modifier = modifier) {
        items(count = categories.size, key = { index -> categories[index].id }) { index ->
            val currentVo = categories[index]
            Row {
                Text(
                    text = currentVo.name,
                    modifier = modifier
                        .padding(8.dp)
                        .weight(1F)
                )
                IconButton(onClick = { onAction(CategoriesAction.SaveItem(item = currentVo)) }) {
                    Icon(Icons.Filled.Favorite, contentDescription = "Favorite")
                }
            }
        }
    }
}
