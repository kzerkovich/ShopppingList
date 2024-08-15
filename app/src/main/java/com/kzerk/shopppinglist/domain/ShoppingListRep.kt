package com.kzerk.shopppinglist.domain

interface ShoppingListRep {
	fun addItemToShopList(item: ShopItem)
	fun getItemById(id: Int): ShopItem
	fun getShopList(): List<ShopItem>
	fun removeItem(item: ShopItem)
	fun updateItem(item: ShopItem)
}