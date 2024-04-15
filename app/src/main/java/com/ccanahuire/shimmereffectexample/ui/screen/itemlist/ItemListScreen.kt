package com.ccanahuire.shimmereffectexample.ui.screen.itemlist

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.Arrangement.spacedBy
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.ccanahuire.shimmereffectexample.domain.model.Item
import com.ccanahuire.shimmereffectexample.ui.placeholder.CirclePlaceholder
import com.ccanahuire.shimmereffectexample.ui.placeholder.TextPlaceholder
import com.ccanahuire.shimmereffectexample.ui.theme.ShimmerEffectExampleTheme

@Composable
fun ItemListScreen(
    modifier: Modifier = Modifier,
    viewModel: ItemListViewModel = viewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    ItemListScreen(
        uiState = uiState,
        modifier = modifier.padding(16.dp)
    )
}

@Composable
fun ItemListScreen(
    uiState: ItemListUiState,
    modifier: Modifier = Modifier
) {
    Crossfade(targetState = uiState, label = "uiState crossfade") { state ->
        when (state) {
            is ItemListUiState.Error -> {
                Text(modifier = modifier, text = "An error occurred.")
            }
            ItemListUiState.Loading -> {
                ItemList(modifier = modifier, items = emptyList(), isLoading = true)
            }
            is ItemListUiState.Success -> {
                ItemList(modifier = modifier, items = state.itemList, isLoading = false)
            }
        }
    }
}

@Composable
private fun ItemList(
    modifier: Modifier = Modifier,
    items: List<Item>,
    isLoading: Boolean
) {
    LazyColumn(
        modifier = modifier,
        verticalArrangement = spacedBy(16.dp)
    ) {
        if (isLoading) {
            items(3) {
                ItemLoadingPlaceholder()
            }
        }
        items(items) { item ->
            ItemComposable(item = item)
        }
    }
}

@Composable
private fun ItemLoadingPlaceholder(
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        CirclePlaceholder(48.dp)
        TextPlaceholder(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 8.dp),
            numLines = 2
        )
    }
}

@Composable
private fun ItemComposable(
    item: Item,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(item.imgUrl)
                .crossfade(true)
                .build(),
            contentDescription = null,
            modifier = Modifier
                .clip(CircleShape)
                .size(48.dp)
        )
        Text(
            modifier = Modifier.padding(start = 8.dp),
            text = item.title
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ItemListScreenPreview() {
    ShimmerEffectExampleTheme {
        ItemListScreen(uiState = ItemListUiState.Loading)
    }
}