package com.getir.patika.getirlite.data.entity

import android.os.Parcelable
import androidx.room.Entity
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import java.io.Serializable

@Parcelize
data class Product(
    val id: String,
    val name: String?,
    val attribute: String?,
    val shortDescription: String?,
    val thumbnailURL: String?,
    val imageURL: String?,
    val price: Double?,
    val priceText: String?,
    val category: String?,
    val productCount: Int?,
    var products: List<Product>?
)
: Parcelable
