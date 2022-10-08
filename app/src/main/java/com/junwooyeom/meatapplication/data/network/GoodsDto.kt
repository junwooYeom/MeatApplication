package com.junwooyeom.meatapplication.data.network

import com.google.gson.annotations.SerializedName

data class GoodsDto(
    @SerializedName("categories")
    val categories: List<CategoryDto>,
    @SerializedName("productions")
    val products: List<ProductDto>
)
