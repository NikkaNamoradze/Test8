package com.example.test8.di

import android.content.Context
import androidx.room.Room
import com.example.test8.data.local.ShopItemsDatabase
import com.example.test8.data.network.ApiService
import com.example.test8.data.repository.ShopRepository
import com.example.test8.data.repository.ShopRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    private const val BASE_URL = "https://run.mocky.io/v3/"

    @Provides
    @Singleton
    fun provideCoursesApi(): ApiService =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): ShopItemsDatabase {
        return Room.databaseBuilder(
            context,
            ShopItemsDatabase::class.java,
            "NotesDB"
        ).build()
    }

    @Provides
    @Singleton
    fun provideShopRepositoryImpl(apiService: ApiService, database: ShopItemsDatabase): ShopRepository {
        return ShopRepositoryImpl(apiService, database)
    }



}