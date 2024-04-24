package com.getir.patika.getirlite.ui.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.getir.patika.getirlite.R
import com.getir.patika.getirlite.data.entity.CartProduct
import com.getir.patika.getirlite.databinding.BasketProductBinding

class BasketAdaptercopy(private val mContext: Context) :
    ListAdapter<CartProduct, BasketAdaptercopy.BasketHolder>(DiffCallback()) {

    inner class BasketHolder(val binding: BasketProductBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BasketHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = BasketProductBinding.inflate(layoutInflater, parent, false)
        return BasketHolder(binding)
    }

    override fun onBindViewHolder(holder: BasketHolder, position: Int) {
        val product = getItem(position)
        Log.e("HHHHHH", product.toString())
        holder.binding.apply {
            basketProductName.text = product.name
            basketPrice.text = "${product.price}"
            Glide.with(mContext).load(product.imageURL)
                .placeholder(R.drawable.def_image)
                .error(R.drawable.def_image)
                .into(imageView3)
        }
    }

    class DiffCallback : DiffUtil.ItemCallback<CartProduct>() {
        override fun areItemsTheSame(oldItem: CartProduct, newItem: CartProduct): Boolean {
            // ID'ye göre eşsizlik kontrolü yapılır.
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: CartProduct, newItem: CartProduct): Boolean {
            // İçeriklerin değişip değişmediği kontrol edilir.
            return oldItem == newItem
        }
    }
}
