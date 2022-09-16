package com.example.test8.data.mapper

import com.example.test8.data.local.ShopItemsEntity
import com.example.test8.data.model.ShopItemDto
import com.example.test8.ui.model.ItemModelUI

fun ShopItemDto.toItemModel() =
    ItemModelUI(
        title = title,
        cover = cover,
        price = price,
        liked = liked
    )

fun ItemModelUI.toItemDto() =
    ShopItemDto(
        title = title,
        cover = cover,
        price = price,
        liked = liked
    )

fun ItemModelUI.toItemEntity() =
    ShopItemsEntity(
        title = title,
        cover = cover,
        price = price,
        liked = liked
    )

fun ShopItemsEntity.toItemModel() =
    ItemModelUI(
        title = title,
        cover = cover,
        price = price,
        liked = liked
    )