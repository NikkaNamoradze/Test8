package com.example.test8.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface ShopItemsDao {

    @Insert
    suspend fun addShopItems(shopItems: List<ShopItemsEntity>)

    @Query("SELECT * FROM shopItems_table")
    suspend fun getAllShopItems(): List<ShopItemsEntity>

    @Query("DELETE FROM shopItems_table")
    suspend fun deleteAll()

}