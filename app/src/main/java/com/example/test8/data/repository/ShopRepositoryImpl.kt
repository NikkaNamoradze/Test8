package com.example.test8.data.repository

import com.example.test8.data.local.ShopItemsDatabase
import com.example.test8.data.local.ShopItemsEntity
import com.example.test8.data.model.ShopItemDto
import com.example.test8.data.network.ApiService
import com.example.test8.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ShopRepositoryImpl @Inject constructor(
    private val api: ApiService,
    private val database: ShopItemsDatabase
) : ShopRepository {
    override suspend fun getData(): Flow<Resource<List<ShopItemDto>>> = flow {
        try {
            val response = api.getData()
            val body = response.body()
            if (response.isSuccessful && body != null) {
                emit(Resource.Success(body))
            } else {
//                d("data_mess", "${response.message()}")
                emit(Resource.Failure(response.message()))
            }
        } catch (e: Throwable) {
//            d("data_mess", "${e.message}")
            emit(Resource.Failure(e.message.toString()))
        }
    }


    override suspend fun getAllShopItems(): Flow<List<ShopItemsEntity>> = flow {
        emit(database.getShopItemsDao().getAllShopItems())
    }

    override suspend fun addShopItems(shopItem: List<ShopItemsEntity>) {
        return database.getShopItemsDao().addShopItems(shopItem)
    }

    override suspend fun deleteAllShopItems() {
        database.getShopItemsDao().deleteAll()
    }
}


