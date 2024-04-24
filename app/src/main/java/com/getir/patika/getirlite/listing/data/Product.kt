package com.getir.patika.getirlite.listing.data
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.io.Serializable

@Entity(tableName = "products")
data class Product(
    @PrimaryKey(autoGenerate = true) @SerializedName("id") val id: Int,
    @ColumnInfo(name = "name") @SerializedName("name") val name: String,
    @ColumnInfo(name = "price") @SerializedName("price") val price: Double,
    @ColumnInfo(name = "imageUrl") @SerializedName("imageUrl") val imageUrl: String,
    @ColumnInfo(name = "count") @SerializedName("count") val count: Int
): Serializable
