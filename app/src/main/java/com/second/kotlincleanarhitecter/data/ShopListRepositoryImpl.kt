package com.second.kotlincleanarhitecter.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.second.kotlincleanarhitecter.domain.ShopRepository
import com.second.kotlincleanarhitecter.domain.item
import kotlin.random.Random

object ShopListRepositoryImpl:ShopRepository {

    private val shoplist = mutableListOf<item>()
    private var autoIncrementId = 0
    val shopListLD = MutableLiveData<List<item>>()

    init {
        for(i in 0 until 10000){
            val item = item("Name $i", i, Random.nextBoolean())
            addItem(item)
        }
    }

    override fun getItemsShop(): LiveData<List<item>> {
        return shopListLD
    }

    override fun deleteItem(shopitem:item) {
        shoplist.remove(shopitem)
        updateLD()
    }

    override fun editItem(item: item) {
        if (item.id == item.UNDEFIEND_ID) {
            val oldItem = getItemId(item.id)
            shoplist.remove(oldItem)
        }
        addItem(item)
    }


    override fun getItemId(shopitemId: Int): item {
        return shoplist.find { it.id == shopitemId }?:throw RuntimeException("Element id $shopitemId not found")
    }

    override fun addItem(item: item) {
        item.id = autoIncrementId++
        shoplist.add(item)
        updateLD()
    }

    private fun updateLD(){
        shopListLD.value = shoplist.toList()
    }
}