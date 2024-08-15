package com.kzerk.shopppinglist.domain

class GetItemByIdUseCase(private val shoppingListRep: ShoppingListRep) {
	fun getItemById(id: Int): ShopItem{
		return shoppingListRep.getItemById(id)
	}
}