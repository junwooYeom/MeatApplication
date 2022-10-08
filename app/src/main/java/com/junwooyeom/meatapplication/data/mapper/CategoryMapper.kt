package com.junwooyeom.meatapplication.data.mapper

import com.junwooyeom.meatapplication.data.network.CategoryDto
import com.junwooyeom.meatapplication.domain.model.Category

fun CategoryDto.toCategory(): Category = Category(
    key = key,
    name = name,
    order = order
)
