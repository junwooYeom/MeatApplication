package com.junwooyeom.meatapplication.domain.model

import androidx.compose.runtime.Immutable

@Immutable
data class Product(
    val key: String,
    val category: String,
    val name: String,
    val price: Int,
    val thumbnail: String,
    val order: Int,
    val favorite: Boolean
) : java.io.Serializable
