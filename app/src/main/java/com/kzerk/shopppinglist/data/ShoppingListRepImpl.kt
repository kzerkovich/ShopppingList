package com.kzerk.shopppinglist.data

import com.kzerk.shopppinglist.domain.ShopItem
import com.kzerk.shopppinglist.domain.ShoppingListRep

object ShoppingListRepImpl: ShoppingListRep {

	private val shopList = mutableListOf<ShopItem>()

	private var autoIncrementId = 0

	override fun addItemToShopList(item: ShopItem) {
		if (item.id == ShopItem.UNDEFINED_ID)
			item.id = autoIncrementId++
		shopList.add(item)
	}

	override fun getItemById(id: Int): ShopItem {
		return shopList.find {
			it.id == id
		} ?: throw RuntimeException("Element with id $id not found")
	}

	override fun getShopList(): List<ShopItem> {
		return shopList.toList()
	}

	override fun removeItem(item: ShopItem) {
		shopList.remove(item)
	}

	override fun updateItem(item: ShopItem) {
		val oldItem = getItemById(item.id)
		shopList.remove(oldItem)
		addItemToShopList(item)
	}

}