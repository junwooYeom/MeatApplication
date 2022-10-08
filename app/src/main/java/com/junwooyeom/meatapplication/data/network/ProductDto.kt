package com.junwooyeom.meatapplication.data.network

import com.google.gson.annotations.SerializedName

data class ProductDto(
    @SerializedName("key")
    val key: String,
    @SerializedName("category_key")
    val category: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("price")
    val price: Int,
    @SerializedName("thumbnail")
    val thumbnail: String,
    @SerializedName("order")
    val order: Int
)
