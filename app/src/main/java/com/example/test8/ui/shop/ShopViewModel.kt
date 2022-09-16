package com.example.test8.ui.shop

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.test8.data.local.ShopItemsEntity
import com.example.test8.data.model.ShopItemDto
import com.example.test8.data.repository.ShopRepository
import com.example.test8.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ShopViewModel @Inject constructor(private val shopRepository: ShopRepository) : ViewModel() {

    private var _courseFlow = MutableStateFlow<Resource<List<ShopItemDto>>>(Resource.Loading())
    val coursesFlow = _courseFlow.asStateFlow()

    fun loadShopItems() {
        viewModelScope.launch {
            shopRepository.getData().collect{
                _courseFlow.emit(it)
            }
        }
    }

    fun addItemsInLocalBase(dataList: List<ShopItemsEntity>) {
        viewModelScope.launch {
            shopRepository.addShopItems(dataList)
        }
    }

    fun loadLocalItems() {
        viewModelScope.launch {

        }
    }

    fun deleteLocalItems() {
        viewModelScope.launch {
            shopRepository.deleteAllShopItems()
        }
    }

    fun isOnline(context: Context): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val capabilities =
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
            } else {
                return false
            }
        if (capabilities != null) {
            if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)) {
                return true
            } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)) {
                return true
            } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)) {
                return true
            }
        }
        return false
    }


}