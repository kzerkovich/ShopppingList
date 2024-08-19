package com.kzerk.shopppinglist.domain

import androidx.lifecycle.LiveData

interface ShoppingListRep {
	fun addItemToShopList(item: ShopItem)
	fun getItemById(id: Int): ShopItem
	fun getShopList(): LiveData<List<ShopItem>>
	fun removeItem(item: ShopItem)
	fun updateItem(item: ShopItem)
}