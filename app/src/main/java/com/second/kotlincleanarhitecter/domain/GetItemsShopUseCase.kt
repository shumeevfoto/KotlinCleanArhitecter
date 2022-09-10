package com.second.kotlincleanarhitecter.domain

import android.provider.LiveFolders
import androidx.lifecycle.LiveData

class GetItemsShopUseCase(private val repository: ShopRepository) {
    fun getItemsShop(): LiveData<List<item>>
    {
        return repository.getItemsShop()
    }
}