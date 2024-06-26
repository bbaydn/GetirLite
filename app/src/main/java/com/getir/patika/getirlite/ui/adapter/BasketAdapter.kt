package com.getir.patika.getirlite.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.getir.patika.getirlite.data.entity.CartProduct
import com.getir.patika.getirlite.data.entity.Product
import com.getir.patika.getirlite.databinding.BasketProductBinding
import com.getir.patika.getirlite.databinding.ProductHorizontalItemBinding


class BasketAdapter( private val onDeleteClick: (CartProduct) -> Unit) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var products: MutableList<CartProduct> = mutableListOf()
    private var suggestedProducts: List<Product> = emptyList()
    var onItemClick: ((Product) -> Unit)? = null
    var onAddItemClick: ((CartProduct) -> Unit)? = null
    fun updateProducts(products: MutableList<CartProduct>) {
        this.products = products
        notifyDataSetChanged()
    }


    fun removeProduct(product: CartProduct) {
        val index = products.indexOf(product)
        if (index != -1) {
            products.removeAt(index) // Ürünü listeden çıkar
            notifyItemRemoved(index)
        }
    }

    fun updateSuggestedProducts(suggestedProducts: List<Product>) {
        this.suggestedProducts = suggestedProducts
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return products.size + suggestedProducts.size
    }

    override fun getItemViewType(position: Int): Int {
        return if (position < products.size) {
            TYPE_PRODUCT
        } else {
            TYPE_SUGGESTED_PRODUCT
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            TYPE_PRODUCT -> {
                val binding = BasketProductBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                VerticalBasketAdapter.VerticalBasketHolder(binding, onDeleteClick)
            }
            TYPE_SUGGESTED_PRODUCT -> {
                val binding = ProductHorizontalItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                SuggestedBasketAdapter.HorizontalViewHolder(binding)
            }
            else -> throw IllegalArgumentException("Invalid view type")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is VerticalBasketAdapter.VerticalBasketHolder -> {
                val product = products[position]
                holder.bind(product)
            }
            is SuggestedBasketAdapter.HorizontalViewHolder -> {
                val suggestedProduct = suggestedProducts[position - products.size]
                holder.bind(suggestedProduct, onItemClick, onAddItemClick)
            }
        }
    }

    companion object {
        private const val TYPE_PRODUCT = 0
        private const val TYPE_SUGGESTED_PRODUCT = 1
    }
}
