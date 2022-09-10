package com.second.kotlincleanarhitecter.presentation

import androidx.lifecycle.ViewModel
import com.second.kotlincleanarhitecter.data.ShopListRepositoryImpl
import com.second.kotlincleanarhitecter.domain.DeleteItemUseCase
import com.second.kotlincleanarhitecter.domain.EditItemUseCase
import com.second.kotlincleanarhitecter.domain.GetItemsShopUseCase
import com.second.kotlincleanarhitecter.domain.item

class MainViewModel:ViewModel() {

    private val repository = ShopListRepositoryImpl

    private val getItemsShopUseCase = GetItemsShopUseCase(repository)
    private val deleteItemUseCase = DeleteItemUseCase(repository)
    private val editItemUseCase = EditItemUseCase(repository)

    val shopList = getItemsShopUseCase.getItemsShop()

    fun deleteItem(shopItem:item) {
        deleteItemUseCase.deleteItem(shopItem)

    }

    fun editItem(shopItem: item){
        val newShopItem = shopItem.copy(enabled = !shopItem.enabled)
        editItemUseCase.editItem(newShopItem)

    }

}
