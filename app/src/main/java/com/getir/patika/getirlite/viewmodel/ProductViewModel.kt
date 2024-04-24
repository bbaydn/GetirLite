package com.getir.patika.getirlite.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.getir.patika.getirlite.data.entity.Product
import com.getir.patika.getirlite.data.repository.ProductRepository
import kotlinx.coroutines.launch

class ProductViewModel(private val repository: ProductRepository): ViewModel() {

    val products = MutableLiveData<List<Product>>()
    val suggestedProducts = MutableLiveData<List<Product>>()

    fun fetchProducts() {
        viewModelScope.launch {
            repository.getProducts()?.let {
                products.value = it
            }
        }
    }

    fun fetchSuggestedProducts() {
        viewModelScope.launch {
            repository.getSuggestedProducts()?.let {
                suggestedProducts.postValue(it)
            }
        }
    }

}


