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
    val attribute: String?, // Ekstra açıklamalar veya özellikler için kullanılabilir
    val shortDescription: String?, // Ürünün kısa açıklaması
    val thumbnailURL: String?, // Ürünün küçük resmi
    val imageURL: String?, // Ürünün büyük resmi
    val price: Double?, // Ürün fiyatı, bazı API yanıtlarında yok
    val priceText: String?, // Fiyatın metin olarak gösterimi, bazı API yanıtlarında yok
    val category: String?, // Ürünün ait olduğu kategori (eğer varsa)
    val productCount: Int?, // Eğer ürün bir kategoriye aitse, o kategorideki ürün sayısı
    var products: List<Product>?
)
: Parcelable
