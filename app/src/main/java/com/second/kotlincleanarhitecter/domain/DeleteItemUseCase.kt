package com.second.kotlincleanarhitecter.domain

class DeleteItemUseCase(private val repository: ShopRepository) {
    fun deleteItem(shopItem: item) {
        repository.deleteItem(shopItem)

    }
}