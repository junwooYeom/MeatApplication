package com.junwooyeom.meatapplication.data.mapper

import com.junwooyeom.meatapplication.data.database.model.ProductEntity
import com.junwooyeom.meatapplication.data.network.ProductDto
import com.junwooyeom.meatapplication.domain.model.Product

fun ProductDto.toProduct(): Product = Product(
    key = key,
    category = category,
    name = name,
    price = price,
    thumbnail = thumbnail,
    order = order,
    favorite = false
)

fun ProductEntity.toProduct(): Product = Product(
    key = key,
    category = category,
    name = name,
    price = price,
    thumbnail = thumbnail,
    order = order,
    favorite = true
)

fun Product.toProductEntity(): ProductEntity = ProductEntity(
    key = key,
    category = category,
    name = name,
    price = price,
    thumbnail = thumbnail,
    order = order,
)
