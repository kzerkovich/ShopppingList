package com.kzerk.shopppinglist.domain

import androidx.lifecycle.LiveData

class GetShopListUseCase(private val shoppingListRep: ShoppingListRep) {

	fun getShopList(): LiveData<List<ShopItem>> {
		return shoppingListRep.getShopList()
	}
}