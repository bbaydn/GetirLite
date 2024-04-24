package com.getir.patika.getirlite.ui.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.getir.patika.getirlite.data.entity.CartProduct
import com.getir.patika.getirlite.data.entity.Product
import com.getir.patika.getirlite.data.repository.CartRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CartViewModel(private val repository: CartRepository) : ViewModel() {
/*    val cartDao = DatabaseClient.getDatabase(requireContext()).cartDao()

    constructor() : this(CartRepository())*/
    private val _products = MutableLiveData<List<CartProduct>>()
//    val products: LiveData<List<CartProduct>> = _products

    private val _suggestedProducts = MutableLiveData<List<Product>>()
    val suggestedProducts: LiveData<List<Product>> = _suggestedProducts

    val products: LiveData<List<CartProduct>> = liveData {
        val data = repository.getAllProduct() // Varsayalım ki bu metod veritabanından ürünleri çekiyor
        emit(data)
    }

    init {
        loadCartItems()
    }

    private fun loadCartItems() {
        viewModelScope.launch(Dispatchers.IO) {
            val products = repository.getAllProduct()
            _products.postValue(products) // Ana thread üzerinde güvenli bir şekilde güncelleme
            Log.e("BUSRRAAA", products.toString())
        }
    }



    fun addToCart(cartProduct: CartProduct) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertProduct(cartProduct)
            repository.getAllProduct()
/*
            loadCartItems()  // Reload the cart items to reflect the updated list
*/
            Log.e("BUSRRAAA22", repository.getAllProduct().toString())

            Log.e("BUSRRAAA2", repository.insertProduct(cartProduct).toString())

        }
    }

    fun removeFromCart(cartProduct: CartProduct) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteProduct(cartProduct)
            loadCartItems()  // Reload the cart items to reflect the updated list
        }
    }
}
