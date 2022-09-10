package com.second.kotlincleanarhitecter.domain

class GetItemIdUseCase(private val repository: ShopRepository) {
    fun getItemId(shopitemId:Int):item{
        return  repository.getItemId(shopitemId)

    }
}