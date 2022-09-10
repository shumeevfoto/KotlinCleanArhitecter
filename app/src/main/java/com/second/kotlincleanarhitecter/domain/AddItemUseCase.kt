package com.second.kotlincleanarhitecter.domain

class AddItemUseCase(private val repository: ShopRepository) {
    fun addItem(item: item){
        repository.addItem(item)

    }
}