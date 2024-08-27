package com.kzerk.shopppinglist.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.kzerk.shopppinglist.R
import com.kzerk.shopppinglist.domain.ShopItem

class ShopListAdapter: RecyclerView.Adapter<ShopListAdapter.ShopItemViewHolder>() {

	var shopList = listOf<ShopItem>()
		set(value) {
			val callback = ShopListDiffCallback(shopList, value)
			val diffResult = DiffUtil.calculateDiff(callback)
			diffResult.dispatchUpdatesTo(this)
			field = value
		}

	var onShopItemLongClickListener: ((ShopItem) -> Unit)? = null
	var onShopItemClickListener: ((ShopItem) -> Unit)? = null

	class ShopItemViewHolder(val view: View): RecyclerView.ViewHolder(view) {
		val tvName = view.findViewById<TextView>(R.id.tv_name)
		val tvCount = view.findViewById<TextView>(R.id.tv_count)
	}

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShopItemViewHolder {
		val layout = when (viewType){
			ENABLED -> R.layout.item_shop_enabled
			DISABLED ->R.layout.item_shop_disabled
			else -> throw RuntimeException("Unknown ViewType $viewType")
		}
		val view = LayoutInflater.from(parent.context).inflate(layout, parent, false)
		return ShopItemViewHolder(view)
	}

	override fun getItemCount(): Int {
		return shopList.size
	}

	override fun getItemViewType(position: Int): Int {
		val item = shopList[position]
		return if (item.enabled) {
			ENABLED
		} else {
			DISABLED
		}
	}

	override fun onBindViewHolder(holder: ShopItemViewHolder, position: Int) {
		val shopItem = shopList[position]
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