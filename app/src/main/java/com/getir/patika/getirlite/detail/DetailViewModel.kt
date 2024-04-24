package com.getir.patika.getirlite.detail

import androidx.lifecycle.ViewModel
import com.getir.patika.getirlite.listing.data.ProductDaoRepository
import javax.inject.Inject

// @Inject constructor
class DetailViewModel () : ViewModel() {

    var prepo= ProductDaoRepository()
    fun addToCartVM (id:Int?, name:String?, price:Double?, imageUrl: String?, count: Int?){
        prepo.addToCart(id, name, price, imageUrl, count)
    }
}