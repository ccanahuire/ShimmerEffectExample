package com.ccanahuire.shimmereffectexample.domain

import com.ccanahuire.shimmereffectexample.domain.model.Item
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class ItemListRepository {
    fun getItemList(): Flow<List<Item>> {
        return flow {
            delay(2000L)
            (0 until 3).map {
                Item(
                    title = "Large item name with an extra description that might take two lines long $it",
                    imgUrl = "https://picsum.photos/200"
                )
            }.let { emit(it) }
        }
    }
}