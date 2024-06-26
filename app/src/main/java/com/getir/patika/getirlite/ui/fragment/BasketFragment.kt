package com.getir.patika.getirlite.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.getir.patika.getirlite.data.entity.DatabaseClient
import com.getir.patika.getirlite.data.repository.CartRepository
import com.getir.patika.getirlite.databinding.FragmentBasketBinding
import com.getir.patika.getirlite.ui.adapter.BasketAdapter
import com.getir.patika.getirlite.viewmodel.CartViewModel
import com.getir.patika.getirlite.viewmodel.CartViewModelFactory


class BasketFragment : Fragment() {
    private lateinit var binding: FragmentBasketBinding
    private lateinit var viewModel: CartViewModel
    private lateinit var adapter: BasketAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentBasketBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val factory = CartViewModelFactory(CartRepository(DatabaseClient.getDatabase(requireContext()).cartDao()))
        viewModel = ViewModelProvider(this, factory).get(CartViewModel::class.java)

        adapter = BasketAdapter() { product ->
            viewModel.deleteProduct(product)
            binding.includedToolbar.tvPrice.text = product.price.toString()
        }

        binding.rvBasket.layoutManager = LinearLayoutManager(requireContext())
        binding.rvBasket.adapter = adapter

        setupToolbar()
        observeViewModel()
    }

    private fun setupToolbar() {
        binding.includedToolbar.imageButtonDeleteAll.visibility = View.VISIBLE
        binding.includedToolbar.closeButton.visibility = View.VISIBLE
        binding.includedToolbar.closeButton.setOnClickListener {
            requireActivity().onBackPressedDispatcher.onBackPressed()
        }
    }

    private fun delete() {
        binding.includedToolbar.imageButtonDeleteAll.setOnClickListener {

        }
    }

    private fun observeViewModel() {
        viewModel.products.observe(viewLifecycleOwner) { products ->
            adapter.updateProducts(products.toMutableList())
        }
        viewModel.products.observe(viewLifecycleOwner) { products ->
            adapter.updateProducts(products.toMutableList())
        }

        viewModel.deleteProductEvent.observe(viewLifecycleOwner) { deletedProduct ->
            adapter.removeProduct(deletedProduct)
        }

    }
}
