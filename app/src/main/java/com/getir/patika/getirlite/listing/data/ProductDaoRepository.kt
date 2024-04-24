package com.getir.patika.getirlite.listing.data

import android.util.Log
import androidx.lifecycle.LiveData
//private val productDao: ProductDao
class ProductDaoRepository() {
    fun addToCart(id: Int?, name: String?, price: Double?, imageUrl: String?, count: Int?) {
        Log.e("BUSRA", name.toString())
    }

   /* val allProducts: LiveData<List<Product>> = productDao.getAllProducts()

    suspend fun insert(product: Product) {
        productDao.insertProduct(product)
    }

    suspend fun delete(product: Product) {
        productDao.deleteProduct(product)
    }

    fun getProductById(id: Int): LiveData<Product> = productDao.getProductById(id)
*/
}