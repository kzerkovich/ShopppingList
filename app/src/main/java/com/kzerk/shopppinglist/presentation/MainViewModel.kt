package com.kzerk.shopppinglist.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kzerk.shopppinglist.data.ShoppingListRepImpl
import com.kzerk.shopppinglist.domain.GetShopListUseCase
import com.kzerk.shopppinglist.domain.RemoveItemUseCase
import com.kzerk.shopppinglist.domain.ShopItem
import com.kzerk.shopppinglist.domain.UpdateItemUseCase

class MainViewModel : ViewModel() {

    private val repository = ShoppingListRepImpl

    private val getShopListUseCase = GetShopListUseCase(repository)
    private val removeItemUseCase = RemoveItemUseCase(repository)
    private val updateItemUseCase = UpdateItemUseCase(repository)

    val shopList = getShopListUseCase.getShopList()


    fun removeItem(shopItem: ShopItem){
        removeItemUseCase.removeItem(shopItem)
    }

    fun changeEnableState(shopItem: ShopItem){
        val changedItem = shopItem.copy(enabled = !shopItem.enabled)
        updateItemUseCase.updateItem(changedItem)
    }

}