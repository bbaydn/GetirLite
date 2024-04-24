package com.getir.patika.getirlite.listing

import android.animation.ObjectAnimator
import android.content.res.Resources
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.DecelerateInterpolator
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.getir.patika.getirlite.R
import com.getir.patika.getirlite.data.entity.CartDao
import com.getir.patika.getirlite.data.entity.DatabaseClient
import com.getir.patika.getirlite.data.repository.CartRepository
import com.getir.patika.getirlite.data.repository.ProductRepository
import com.getir.patika.getirlite.databinding.FragmentListBinding
import com.getir.patika.getirlite.ui.adapter.MainAdapter
import com.getir.patika.getirlite.ui.viewmodel.ProductViewModel
import com.getir.patika.getirlite.ui.viewmodel.ProductViewModelFactory
import com.getir.patika.getirlite.retrofit.RetrofitClient // Bu RetrofitClient'ınızın implementasyonunu varsayıyorum
import com.getir.patika.getirlite.ui.viewmodel.CartViewModel
import com.getir.patika.getirlite.ui.viewmodel.CartViewModelFactory

class ListFragment : Fragment() {
    private var _binding: FragmentListBinding? = null
    private val binding get() = _binding!!
    val repository = ProductRepository(RetrofitClient().api)
    private lateinit var cartDao: CartDao
    private lateinit var cartRepository: CartRepository
    private lateinit var mainAdapter: MainAdapter

    private val viewModel: ProductViewModel by viewModels {
        ProductViewModelFactory(repository)
    }

    private lateinit var cartViewModel: CartViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentListBinding.inflate(inflater, container, false)

        setupRecyclerView()
        setupToolbar()
        observeViewModel()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        cartDao = DatabaseClient.getDatabase(requireContext()).cartDao()
        cartRepository = CartRepository(cartDao)
        cartViewModel = ViewModelProvider(this, CartViewModelFactory(cartRepository)).get(CartViewModel::class.java)

    }

    private fun setupRecyclerView() {
        binding.mainRecyclerView.apply {
            layoutManager = LinearLayoutManager(context)
        }
    }

    private fun setupToolbar() {
        binding.includedToolbar.goToCart.setOnClickListener {
            findNavController().navigate(R.id.action_listFragment_to_basketFragment)
        }
    }

    private fun observeViewModel() {
        viewModel.products.observe(viewLifecycleOwner, Observer { products ->
            viewModel.suggestedProducts.observe(viewLifecycleOwner, Observer { suggestedProducts ->
                mainAdapter = MainAdapter(suggestedProducts, products, requireContext(), cartViewModel) {
                    toolbarItemVisible(true)
                }
                binding.mainRecyclerView.adapter = mainAdapter
                mainAdapter.updateProducts(products)
                mainAdapter.updateSuggestedProducts(suggestedProducts)
            })
        })

        viewModel.fetchProducts()
        viewModel.fetchSuggestedProducts()
    }

    private fun toolbarItemVisible(isVisible: Boolean) {
        if (binding.includedToolbar.goToCart.visibility == View.VISIBLE) {

        } else {
            val visibility = if (isVisible) View.VISIBLE else View.GONE
            val goToCart = binding.includedToolbar.goToCart
            goToCart.visibility = visibility

            val screenWidth = Resources.getSystem().displayMetrics.widthPixels
            val animator = ObjectAnimator.ofFloat(goToCart, "translationX", screenWidth.toFloat(), 0f)
            animator.duration = 500
            animator.interpolator = DecelerateInterpolator()
            animator.start()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null // Clear the binding to avoid memory leaks
    }
}
