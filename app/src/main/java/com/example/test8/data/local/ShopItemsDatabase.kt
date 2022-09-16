package com.example.test8.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [ShopItemsEntity::class], version = 1)
abstract class ShopItemsDatabase: RoomDatabase() {
    abstract fun getShopItemsDao(): ShopItemsDao
}