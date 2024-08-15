package com.kzerk.shopppinglist.domain

class RemoveItemUseCase(private val shoppingListRep: ShoppingListRep) {

	fun removeItem(item: ShopItem){
		shoppingListRep.removeItem(item)
	}
}