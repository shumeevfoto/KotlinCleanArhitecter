package com.second.kotlincleanarhitecter.presentation

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.FragmentContainerView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.second.kotlincleanarhitecter.R
import com.second.kotlincleanarhitecter.domain.item

private lateinit var viewModel: MainViewModel
private lateinit var shopListAdapter: ShopListAdapter
private var shopItemConteiner:FragmentContainerView?=null


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupRecyclerView()
        shopItemConteiner = findViewById(R.id.shop_item_container)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        viewModel.shopList.observe(this) {
            shopListAdapter.shopList = it
        }
        val buttonAdd:FloatingActionButton = findViewById(R.id.floatingActionButton)
        buttonAdd.setOnClickListener{

            val intent = ShopItemActivity.newIntentAddItem(this)
            startActivity(intent)
        }
    }




    private fun setupRecyclerView() {
        val rvShopList = findViewById<RecyclerView>(R.id.recyclerView)
        shopListAdapter = ShopListAdapter()
        rvShopList.adapter = shopListAdapter
        rvShopList.recycledViewPool.setMaxRecycledViews(ShopListAdapter.ENABLED, ShopListAdapter.MAX_POOL)
        rvShopList.recycledViewPool.setMaxRecycledViews(ShopListAdapter.DISABLED, ShopListAdapter.MAX_POOL)
        shopListAdapter.onShopItemLongClic = object:ShopListAdapter.onShopItemLongClicListener{
            override fun onShopItemLongClic(item: item) {
                viewModel.editItem(shopItem = item)
            }

        }
    }

}