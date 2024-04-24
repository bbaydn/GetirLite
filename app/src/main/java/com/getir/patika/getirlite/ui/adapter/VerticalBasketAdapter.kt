package com.getir.patika.getirlite.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.getir.patika.getirlite.R
import com.getir.patika.getirlite.data.entity.CartProduct
import com.getir.patika.getirlite.data.entity.Product
import com.getir.patika.getirlite.databinding.BasketProductBinding

class VerticalBasketAdapter(private var products: List<CartProduct>) :
    ListAdapter<CartProduct, VerticalBasketAdapter.VerticalBasketHolder>(ProductDiffCallback()) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VerticalBasketHolder {
        val binding = BasketProductBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return VerticalBasketHolder(binding)
    }

    override fun onBindViewHolder(holder: VerticalBasketHolder, position: Int) {
        holder.bind(getItem(position))
    }

    fun submitListGrid(products: List<CartProduct>) {
        this.products = products
        submitList(products)
    }

    class VerticalBasketHolder(private val binding: BasketProductBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(product: CartProduct) {
            binding.basketProductName.text = product.name ?: "N/A"

            Glide.with(itemView.context)
                .load(product.imageURL)
                .placeholder(R.drawable.def_image)
                .error(R.drawable.def_image)
                .into(binding.imageView3)

/*
            binding.imageButtonAddProduct.setOnClickListener {
                if (product.products.isNullOrEmpty()) {
                    product.products = listOf(
                        Product(id = "1",
                            name = product.name,
                            price = product.price,
                            attribute = product.attribute,
                            imageURL = product.imageURL,
                            shortDescription = product.shortDescription,
                            thumbnailURL = product.thumbnailURL,
                            priceText = product.priceText,
                            category = product.category,
                            productCount = product.productCount,
                            products = emptyList()),
                    )
                }
                product.products?.let { products ->
                    for (productItem in products.withIndex()) {
                        val cartProduct = CartProduct(
                            name = productItem.value.name,
                            price = productItem.value.price,
                            attributes = productItem.value.attribute,
                            imageURL = productItem.value.imageURL,
                            totalPrice = productItem.value.price?.toInt()
                        )
                        viewModelCartViewModel.addToCart(cartProduct)
                        onAddButtonClick.invoke(cartProduct)
                    }
                } ?: run {
                    Log.d("ImageButtonAddProduct", "Product list is null or empty.")
                }
            }
*/
        }
    }


    class ProductDiffCallback : DiffUtil.ItemCallback<CartProduct>() {
        override fun areItemsTheSame(oldItem: CartProduct, newItem: CartProduct): Boolean = oldItem.id == newItem.id
        override fun areContentsTheSame(oldItem: CartProduct, newItem: CartProduct): Boolean = oldItem == newItem
    }
}
