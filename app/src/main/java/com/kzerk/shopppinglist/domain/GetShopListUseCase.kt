package com.kzerk.shopppinglist.domain

class GetShopListUseCase(private val shoppingListRep: ShoppingListRep) {

	fun getShopList(): List<ShopItem>{
		return shoppingListRep.getShopList()
	}
}