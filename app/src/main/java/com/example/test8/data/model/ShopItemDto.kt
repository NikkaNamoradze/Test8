package com.example.test8.data.model


import com.google.gson.annotations.SerializedName

data class ShopItemDto(
    @SerializedName("cover")
    val cover: String?,
    @SerializedName("liked")
    val liked: Boolean?,
    @SerializedName("price")
    val price: String?,
    @SerializedName("title")
    val title: String?
)