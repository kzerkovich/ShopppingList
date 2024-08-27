package com.kzerk.shopppinglist.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.kzerk.shopppinglist.domain.ShopItem
import com.kzerk.shopppinglist.domain.ShoppingListRep
import kotlin.random.Random

object ShoppingListRepImpl: ShoppingListRep {

	private val shopListLD = MutableLiveData<List<ShopItem>>()

	private val shopList = sortedSetOf<ShopItem>({ o1, o2 -> o1.id.compareTo(o2.id) })

	private var autoIncrementId = 0

	init{
		for (i in 0 until 1000){
			val item = ShopItem("Name $i", i, Random.nextBoolean())
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