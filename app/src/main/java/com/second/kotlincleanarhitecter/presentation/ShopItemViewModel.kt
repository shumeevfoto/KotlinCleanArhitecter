package com.second.kotlincleanarhitecter.presentation

import android.content.ClipData
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.second.kotlincleanarhitecter.data.ShopListRepositoryImpl
import com.second.kotlincleanarhitecter.domain.*

class ShopItemViewModel : ViewModel() {
    private val repository = ShopListRepositoryImpl

    private val editItemUseCase = EditItemUseCase(repository)
    private val addItemUseCase = AddItemUseCase(repository)
    private val getItemsShopUseCase = GetItemsShopUseCase(repository)

    private var _errorInputName = MutableLiveData<Boolean>()
    val errorInputName: LiveData<Boolean>
        get() = _errorInputName

    private var _errorInputCount = MutableLiveData<Boolean>()
    val errorInputCount: LiveData<Boolean>
        get() = _errorInputCount

    private var _shoudCloseScreen = MutableLiveData<Unit>()
    val shoudCloseScreen: LiveData<Unit>
        get() = _shoudCloseScreen


    fun editItem(inputName: String?, inputCount: String?) {
        val name = parseName(inputName)
        val count = parseCount(inputCount)
        val res = fieldsValid(name, count)
        if (res) {
            _shopItem.value?.let {
                val item = it.copy(name = name, count = count)
                editItemUseCase.editItem(item)
                finishScreen()
            }

        }
    }

    private val _shopItem = MutableLiveData<item>()
    val shopItem: LiveData<item>
        get() = _shopItem

//    fun getShopItem(shopItemId: Int) {
//        val item = getItemsShopUseCase.getItemsShop(shopItemId)
//        _shopItem.value = item
//    }

    fun addShopItem(inputName: String?, inputCount: String?) {
        val name = parseName(inputName)
        val count = parseCount(inputCount)
        val res = fieldsValid(name, count)
        if (res) {
            val shopItem = item(name, count, true)
            addItemUseCase.addItem(shopItem)
            finishScreen()
        }
    }

    fun parseName(inputName: String?): String {
        return inputName?.trim() ?: ""
    }

    fun parseCount(inputCount: String?): Int {
        return try {
            inputCount?.trim()?.toInt() ?: 0
        } catch (e: Exception) {
            0
        }
    }

    fun fieldsValid(inputName: String, inputCount: Int): Boolean {
        var result = true
        if (inputName.isBlank()) {
            _errorInputName.value = true
            result = false
        }
        if (inputCount <= 0) {
            _errorInputCount.value = true
            result = false
        }
        return result
    }

    fun resetErrorIntupName() {
        _errorInputName.value = false
    }

    fun resetErrorIntupCount() {
        _errorInputCount.value = false
    }

    fun finishScreen() {
        _shoudCloseScreen.value = Unit
    }
}

