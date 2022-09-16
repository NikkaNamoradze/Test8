package com.example.test8.data.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "shopItems_table")
data class ShopItemsEntity(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int? = null,

    @ColumnInfo(name = "cover")
    val cover: String?,

    @ColumnInfo(name = "liked")
    val liked: Boolean?,

    @ColumnInfo(name = "price")
    val price: String?,

    @ColumnInfo(name = "title")
    val title: String?,

)
