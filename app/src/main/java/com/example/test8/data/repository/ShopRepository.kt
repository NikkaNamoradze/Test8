package com.example.test8.data.repository

import com.example.test8.data.local.ShopItemsEntity
import com.example.test8.data.model.ShopItemDto
import com.example.test8.util.Resource
import kotlinx.coroutines.flow.Flow

interface ShopRepository {
    suspend fun getData(): Flow<Resource<List<ShopItemDto>>>

    suspend fun getAllShopItems(): Flow<List<ShopItemsEntity>>

    suspend fun addShopItems(shopItem: List<ShopItemsEntity>)

    suspend fun deleteAllShopItems()
}