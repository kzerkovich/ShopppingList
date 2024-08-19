package com.kzerk.shopppinglist.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.kzerk.shopppinglist.domain.ShopItem
import com.kzerk.shopppinglist.domain.ShoppingListRep

object ShoppingListRepImpl: ShoppingListRep {

	private val shopListLD = MutableLiveData<List<ShopItem>>()

	private val shopList = mutableListOf<ShopItem>()

	private var autoIncrementId = 0

	init{
		for (i in 0 until 10){
			val item = ShopItem("Name $i", i, true)
			addItemToShopList(item)
		}
	}

	override fun addItemToShopList(item: ShopItem) {
		if (item.id == ShopItem.UNDEFINED_ID)
			item.id = autoIncrementId++
		shopList.add(item)
		updateList()
	}

	override fun getItemById(id: Int): ShopItem {
		return shopList.find {
			it.id == id
		} ?: throw RuntimeException("Element with id $id not found")
	}

	override fun getShopList(): LiveData<List<ShopItem>> {
		return shopListLD
	}

	private fun updateList(){
		shopListLD.value = shopList.toList()
	}

	override fun removeItem(item: ShopItem) {
		shopList.remove(item)
		updateList()
	}

	override fun updateItem(item: ShopItem) {
		val oldItem = getItemById(item.id)
		shopList.remove(oldItem)
		addItemToShopList(item)
	}

}