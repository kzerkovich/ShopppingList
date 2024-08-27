package com.kzerk.shopppinglist.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.kzerk.shopppinglist.R
import com.kzerk.shopppinglist.domain.ShopItem

class ShopListAdapter:ListAdapter<ShopItem, ShopItemViewHolder>(ShopItemDiffCallback()) {

	var onShopItemLongClickListener: ((ShopItem) -> Unit)? = null
	var onShopItemClickListener: ((ShopItem) -> Unit)? = null

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShopItemViewHolder {
		val layout = when (viewType){
			ENABLED -> R.layout.item_shop_enabled
			DISABLED ->R.layout.item_shop_disabled
			else -> throw RuntimeException("Unknown ViewType $viewType")
		}
		val view = LayoutInflater.from(parent.context).inflate(layout, parent, false)
		return ShopItemViewHolder(view)
	}

	override fun getItemViewType(position: Int): Int {
		val item = getItem(position)
		return if (item.enabled) {
			ENABLED
		} else {
			DISABLED
		}
	}

	override fun onBindViewHolder(holder: ShopItemViewHolder, position: Int) {
		val shopItem = getItem(position)
		holder.view.setOnLongClickListener {
			onShopItemLongClickListener?.invoke(shopItem)
			true
		}

		holder.view.setOnClickListener {
			onShopItemClickListener?.invoke(shopItem)
		}

		holder.tvName.text = shopItem.name
		holder.tvCount.text = shopItem.count.toString()
	}

	companion object {
		const val ENABLED = 0
		const val DISABLED = 1

		const val MAX_POOL_SIZE = 30
	}
}