package com.getir.patika.getirlite.ui.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.getir.patika.getirlite.R
import com.getir.patika.getirlite.data.entity.CartProduct
import com.getir.patika.getirlite.data.entity.Product
import com.getir.patika.getirlite.databinding.ProductGridItemBinding
import com.getir.patika.getirlite.ui.viewmodel.CartViewModel

class GridAdapter(private var products: List<Product>,
                  private val viewModelCartViewModel: CartViewModel,
                  private val onAddButtonClick: (CartProduct) -> Unit,
                   ) :
    ListAdapter<Product, GridAdapter.GridViewHolder>(ProductDiffCallback()) {

    var onItemClick: ((Product) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GridViewHolder {
        val binding = ProductGridItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return GridViewHolder(binding, onItemClick, onAddButtonClick, viewModelCartViewModel)
    }

    override fun onBindViewHolder(holder: GridViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    fun submitListGrid(products: List<Product>) {
        this.products = products
        submitList(products)
    }

    class GridViewHolder(
        private val binding: ProductGridItemBinding,
        private val onItemClick: ((Product) -> Unit)?,
        private val onAddButtonClick: (CartProduct) -> Unit,
        private val viewModelCartViewModel: CartViewModel
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(product: Product) {
            binding.textViewProductNameGrid.text = product.name ?: "N/A"

            Glide.with(itemView.context)
                .load(product.thumbnailURL)
                .placeholder(R.drawable.def_image)
                .error(R.drawable.def_image)
                .into(binding.imageViewProductGrid)

          itemView.setOnClickListener { onItemClick?.invoke(product) }
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
            }
        }


    class ProductDiffCallback : DiffUtil.ItemCallback<Product>() {
        override fun areItemsTheSame(oldItem: Product, newItem: Product): Boolean = oldItem.id == newItem.id
        override fun areContentsTheSame(oldItem: Product, newItem: Product): Boolean = oldItem == newItem
    }
}
