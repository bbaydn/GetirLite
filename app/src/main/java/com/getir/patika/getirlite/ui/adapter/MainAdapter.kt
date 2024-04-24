package com.getir.patika.getirlite.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.getir.patika.getirlite.data.entity.Category
import com.getir.patika.getirlite.data.entity.Product
import com.getir.patika.getirlite.databinding.GridListItemBinding
import com.getir.patika.getirlite.databinding.HorizontalListItemBinding
import com.getir.patika.getirlite.listing.ListFragmentDirections
import com.getir.patika.getirlite.ui.viewmodel.CartViewModel

class MainAdapter(
    private var recommendedProducts: List<Product>,
    private var allProducts: List<Product>,
    private val mContext: Context,
    private val viewModel: CartViewModel,
    private val onToolbarItemVisibilityChange: (Boolean) -> Unit,
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val horizontalAdapter = HorizontalAdapter(recommendedProducts, mContext) {
        onToolbarItemVisibilityChange(true)
    }
    private val gridAdapter = GridAdapter(allProducts, viewModel) { product ->
        onToolbarItemVisibilityChange(true) // Make the toolbar item visible when add button is clicked
    }

    companion object {
        private const val TYPE_HORIZONTAL_LIST = 0
        private const val TYPE_GRID = 1
    }

    override fun getItemViewType(position: Int): Int {
        return if (position == 0) TYPE_HORIZONTAL_LIST else TYPE_GRID
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return when (viewType) {
            TYPE_HORIZONTAL_LIST -> HorizontalViewHolder(
                HorizontalListItemBinding.inflate(layoutInflater, parent, false),
                horizontalAdapter
            )
            TYPE_GRID -> GridViewHolder(
                GridListItemBinding.inflate(layoutInflater, parent, false),
                gridAdapter
            )
            else -> throw IllegalArgumentException("Invalid view type")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is HorizontalViewHolder -> holder.bind()
            is GridViewHolder -> holder.bind()
        }
    }

    override fun getItemCount(): Int = 2

    fun updateProducts(products: List<Product>) {
        this.allProducts = products
        gridAdapter.submitListGrid(products)
    }


    fun updateSuggestedProducts(suggestedProducts: List<Product>) {
        this.recommendedProducts = suggestedProducts
        horizontalAdapter.submitList(suggestedProducts)
    }

    inner class HorizontalViewHolder(private val binding: HorizontalListItemBinding, private val adapter: HorizontalAdapter) : RecyclerView.ViewHolder(binding.root) {
        init {
            binding.horizontalRecyclerview.layoutManager = LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false)
            binding.horizontalRecyclerview.adapter = adapter
        }

        fun bind() {
            adapter.onItemClick = { product ->
                val action = ListFragmentDirections.actionListFragmentToDetailFragment(product)
                itemView.findNavController().navigate(action)
            }
        }
    }

    inner class GridViewHolder(private val binding: GridListItemBinding, private val adapter: GridAdapter) : RecyclerView.ViewHolder(binding.root) {
        init {
            binding.gridRecyclerView.layoutManager = GridLayoutManager(mContext, 3)
            binding.gridRecyclerView.adapter = adapter
        }

        fun bind() {
            adapter.onItemClick = { product ->
                val action = ListFragmentDirections.actionListFragmentToDetailFragment(product)
                itemView.findNavController().navigate(action)
            }
        }
    }
}
