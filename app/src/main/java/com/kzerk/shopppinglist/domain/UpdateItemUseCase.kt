package com.kzerk.shopppinglist.domain

class UpdateItemUseCase(private val shoppingListRep: ShoppingListRep) {
	fun updateItem(item: ShopItem){
		shoppingListRep.updateItem(item)
	}
}