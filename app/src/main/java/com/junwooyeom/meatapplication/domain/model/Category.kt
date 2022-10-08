package com.junwooyeom.meatapplication.domain.model

import androidx.compose.runtime.Immutable

@Immutable
data class Category(
    val key: String,
    val name: String,
    val order: Int
)
