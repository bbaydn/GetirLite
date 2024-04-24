package com.getir.patika.getirlite.ui.adapter


import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.getir.patika.getirlite.R
import com.getir.patika.getirlite.data.entity.CartProduct
import com.getir.patika.getirlite.data.entity.Product
import com.getir.patika.getirlite.databinding.ProductHorizontalItemBinding

class SuggestedBasketAdapter(private var products: List<Product>,
                        mContext: Context,
                        private val onAddButtonClick: ((CartProduct) -> Unit)) : RecyclerView.Adapter<SuggestedBasketAdapter.HorizontalViewHolder>() {

    var onItemClick: ((Product) -> Unit)? = null  // Optional: For item click handling
    private lateinit var binding: ProductHorizontalItemBinding
    val layoutInflater = LayoutInflater.from(mContext)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HorizontalViewHolder {
        binding = ProductHorizontalItemBinding.inflate(layoutInflater, parent, false)
        return HorizontalViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HorizontalViewHolder, position: Int) {
        val product = products[position]
        holder.bind(product, onItemClick, onAddButtonClick)
    }

    override fun getItemCount(): Int = products.size

    fun submitList(products: List<Product>) {
        this.products = products
        notifyDataSetChanged()
    }

    class HorizontalViewHolder(val binding: ProductHorizontalItemBinding) : RecyclerView.ViewHolder(binding.root) {
        private val imageView: ImageView = binding.imageViewProduct
        private val textViewName: TextView = binding.textViewProductName
        private val button: ImageButton = binding.imageButtonAddProduct
        private val item = binding.cvHorizontalProductItem


        fun bind(product: Product, onItemClick: ((Product) -> Unit)?, onAddButtonClick: ((CartProduct) -> Unit)?) {
            textViewName.text = product.name
            Glide.with(itemView.context)
                .load(product.imageURL)
                .placeholder(R.drawable.def_image)
                .error(R.drawable.def_image)
                .into(imageView)
            val cartProduct: CartProduct = CartProduct(1, product.name, product.price, product.attribute, product.imageURL, product.price?.toInt())

            item.setOnClickListener { onItemClick?.invoke(product) }
            button.setOnClickListener { onAddButtonClick?.invoke(cartProduct) }
        }
    }
}
