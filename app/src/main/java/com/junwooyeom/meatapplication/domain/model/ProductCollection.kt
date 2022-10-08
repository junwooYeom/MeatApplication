package com.junwooyeom.meatapplication.domain.model

import androidx.compose.runtime.Immutable

@Immutable
data class ProductCollection(
    val key: String,
    val name: String,
    val orderKey: Int,
    val products: List<Product>,
)
