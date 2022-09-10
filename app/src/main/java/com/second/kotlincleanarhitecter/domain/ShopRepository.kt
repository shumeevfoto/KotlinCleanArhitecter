package com.second.kotlincleanarhitecter.domain

import androidx.lifecycle.LiveData

interface ShopRepository {
    fun getItemsShop(): LiveData<List<item>>
    fun deleteItem(shopitem: item)
    fun editItem(item: item)
    fun getItemId(shopitemId:Int):item
    fun addItem(item: item)

}