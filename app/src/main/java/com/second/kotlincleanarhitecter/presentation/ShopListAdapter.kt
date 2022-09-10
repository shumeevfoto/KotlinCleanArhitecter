package com.second.kotlincleanarhitecter.presentation

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.second.kotlincleanarhitecter.R
import com.second.kotlincleanarhitecter.domain.item
import com.second.kotlincleanarhitecter.presentation.ShopListAdapter.*

class ShopListAdapter : RecyclerView.Adapter<ShopItemViewHolder>() {
    var onShopItemLongClic: onShopItemLongClicListener? = null
    var count = 0
    var shopList = listOf<item>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    class ShopItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tv_name = view.findViewById<TextView>(R.id.textViewTitle)
        val tv_count = view.findViewById<TextView>(R.id.textViewCount)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShopItemViewHolder {
        Log.d("onCreateViewHolder", "create ${++count}" )
        val itemXml = when (viewType) {
            ENABLED -> R.layout.item_shop_enabled
            DISABLED -> R.layout.item_shop_disabled
            else -> throw RuntimeException("Unknow view Type ${viewType}")
        }
        val view = LayoutInflater.from(parent.context).inflate(
            itemXml,
            parent,
            false
        )
        return ShopItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: ShopItemViewHolder, position: Int) {
        val shoplist = shopList[position]
        holder.tv_name.text = shoplist.name
        holder.tv_count.text = shoplist.count.toString()
        holder.itemView.setOnClickListener {
            onShopItemLongClic?.onShopItemLongClic(shoplist)
        }
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

    interface onShopItemLongClicListener{
        fun onShopItemLongClic(item: item)
    }

    companion object {
        const val ENABLED = 1
        const val DISABLED = 0
        const val MAX_POOL = 15
    }

}
