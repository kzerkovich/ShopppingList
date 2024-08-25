package com.kzerk.shopppinglist.presentation

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.widget.LinearLayout
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.LayoutInflaterCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import com.kzerk.shopppinglist.R
import com.kzerk.shopppinglist.domain.ShopItem

class MainActivity : AppCompatActivity() {

	private lateinit var viewModel: MainViewModel
	private lateinit var ll_shopList: LinearLayout

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		enableEdgeToEdge()
		setContentView(R.layout.activity_main)
		viewModel = ViewModelProvider(this)[MainViewModel::class.java]
		viewModel.shopList.observe(this){
			showList(it)
		}
	}

	private fun showList(list: List<ShopItem>){
		for(shopItem in list) {
			val layoutId = if (shopItem.enabled){
				R.layout.item_shop_enabled
			}
			else {
				R.layout.item_shop_disabled
			}

			val view = LayoutInflater.from(this).inflate(layoutId, ll_shopList, false)
			val tvName = findViewById<TextView>(R.id.tv_name)
			val tvCount = findViewById<TextView>(R.id.tv_count)
			tvName.text = shopItem.name
			tvCount.text = shopItem.count.toString()
			ll_shopList.addView(view)
		}
	}
}