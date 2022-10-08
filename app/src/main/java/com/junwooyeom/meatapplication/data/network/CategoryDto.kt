package com.junwooyeom.meatapplication.data.network

import com.google.gson.annotations.SerializedName

data class CategoryDto(
    @SerializedName("key")
    val key: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("order")
    val order: Int
)
