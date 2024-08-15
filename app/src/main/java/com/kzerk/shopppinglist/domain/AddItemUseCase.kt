package com.kzerk.shopppinglist.domain

class AddItemUseCase(private val ShoppingListRep: ShoppingListRep) {

	fun addItemToShopList(item: ShopItem){
		ShoppingListRep.addItemToShopList(item)
	}
}