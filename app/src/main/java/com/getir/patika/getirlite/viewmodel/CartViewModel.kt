package com.getir.patika.getirlite.viewmodel

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

class CartViewModel(private val repository: CartRepository) : ViewModel() {
    private val _products = MutableLiveData<List<CartProduct>>()
//    val products: LiveData<List<CartProduct>> = _products

    private val _suggestedProducts = MutableLiveData<List<Product>>()
    val suggestedProducts: LiveData<List<Product>> = _suggestedProducts

    val products: LiveData<List<CartProduct>> = liveData {
        val data = repository.getAllProduct()
        emit(data)
    }
    val deleteProductEvent = MutableLiveData<CartProduct>()


    init {
        loadCartItems()
    }

    private fun loadCartItems() {
        viewModelScope.launch(Dispatchers.IO) {
            val products = repository.getAllProduct()
            _products.postValue(products)
        }
    }

    fun addToCart(cartProduct: CartProduct) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertProduct(cartProduct)
            repository.getAllProduct()
        }
    }

    fun deleteProduct(product: CartProduct) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteProduct(product)
            deleteProductEvent.postValue(product)
        }
    }
}