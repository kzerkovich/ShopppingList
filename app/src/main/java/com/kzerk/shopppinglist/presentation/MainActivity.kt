package com.kzerk.shopppinglist.presentation

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.kzerk.shopppinglist.R

class MainActivity : AppCompatActivity() {

	private lateinit var viewModel: MainViewModel
	private lateinit var adapterSL: ShopListAdapter

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		enableEdgeToEdge()
		setContentView(R.layout.activity_main)
		setupRecycleView()
		viewModel = ViewModelProvider(this)[MainViewModel::class.java]
		viewModel.shopList.observe(this) {
			adapterSL.submitList(it)
		}
		val buttonAddItem = findViewById<FloatingActionButton>(R.id.button_add_shop_item)
		buttonAddItem.setOnClickListener {
			val intent = ShopItemActivity.newIntentAddItem(this)
			startActivity(intent)
		}
	}

	private fun setupRecycleView() {
		val rvShopList = findViewById<RecyclerView>(R.id.rv_shop_list)
		with(rvShopList) {
			adapterSL = ShopListAdapter()
			adapter = adapterSL
			recycledViewPool.setMaxRecycledViews(
				ShopListAdapter.ENABLED,
				ShopListAdapter.MAX_POOL_SIZE
			)
			recycledViewPool.setMaxRecycledViews(
				ShopListAdapter.ENABLED,
				ShopListAdapter.MAX_POOL_SIZE
			)
		}

		setupLongClickListener()
		setupClickListener()
		setupItemTouch(rvShopList)
	}

	private fun setupLongClickListener() {
		adapterSL.onShopItemLongClickListener = {
			viewModel.changeEnableState(it)
		}
	}

	private fun setupClickListener() {
		adapterSL.onShopItemClickListener = {
			val intent = ShopItemActivity.newIntentEditItem(this, it.id)
			startActivity(intent)
		}
	}

	private fun setupItemTouch(rvShopList: RecyclerView) {
		val callback = object : ItemTouchHelper.SimpleCallback(
			0,
			ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
		) {
			override fun onMove(
				recyclerView: RecyclerView,
				viewHolder: RecyclerView.ViewHolder,
				target: RecyclerView.ViewHolder
			): Boolean {
				return false
			}

			override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
				val item = adapterSL.currentList[viewHolder.adapterPosition]
				viewModel.removeItem(item)
			}
		}

		val itemTouchHelper = ItemTouchHelper(callback)
		itemTouchHelper.attachToRecyclerView(rvShopList)
	}
}