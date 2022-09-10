package com.second.kotlincleanarhitecter.domain

class EditItemUseCase(private val repository: ShopRepository) {
    fun editItem(item: item){
        repository.editItem(item)

    }
}