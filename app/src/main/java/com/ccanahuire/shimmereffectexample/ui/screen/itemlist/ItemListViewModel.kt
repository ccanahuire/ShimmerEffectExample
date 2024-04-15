package com.ccanahuire.shimmereffectexample.ui.screen.itemlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ccanahuire.shimmereffectexample.domain.ItemListRepository
import com.ccanahuire.shimmereffectexample.domain.model.Item
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn


class ItemListViewModel : ViewModel() {
    private val itemListRepository = ItemListRepository()

    val uiState = itemListRepository.getItemList()
        .map<List<Item>, ItemListUiState>(ItemListUiState::Success)
        .catch { emit(ItemListUiState.Error(it)) }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), ItemListUiState.Loading)
}

sealed interface ItemListUiState {
    data object Loading : ItemListUiState
    data class Success(val itemList: List<Item>) : ItemListUiState
    data class Error(val throwable: Throwable) : ItemListUiState
}