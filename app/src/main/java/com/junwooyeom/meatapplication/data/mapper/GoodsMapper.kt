package com.junwooyeom.meatapplication.data.mapper

import com.junwooyeom.meatapplication.data.network.GoodsDto
import com.junwooyeom.meatapplication.domain.model.Goods

fun GoodsDto.toGoods(): Goods =
    Goods(
        categories = categories.map { it.toCategory() },
        products = products.map { it.toProduct() }
    )
