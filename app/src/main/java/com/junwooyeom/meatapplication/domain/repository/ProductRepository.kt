package com.junwooyeom.meatapplication.domain.repository

import com.junwooyeom.meatapplication.domain.model.Goods
import com.junwooyeom.meatapplication.domain.model.NetworkResult
import com.junwooyeom.meatapplication.domain.model.Product
import kotlinx.coroutines.flow.Flow

interface ProductRepository {
    val productList: List<Product>

    fun getList(): Flow<NetworkResult<Goods>>

    fun getFavoriteList(): Flow<List<Product>>

    suspend fun insertProduct(product: Product)

    suspend fun deleteProductById(key: String)
}
