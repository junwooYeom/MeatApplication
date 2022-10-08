package com.junwooyeom.meatapplication.domain.usecase

import com.junwooyeom.meatapplication.domain.model.NetworkResult
import com.junwooyeom.meatapplication.domain.repository.ProductRepository
import com.junwooyeom.meatapplication.domain.model.Goods
import com.junwooyeom.meatapplication.domain.model.Product
import com.junwooyeom.meatapplication.domain.model.ProductCollection
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import javax.inject.Inject

@ViewModelScoped
class GetProductsUseCase @Inject constructor(
    private val repository: ProductRepository
) {
    operator fun invoke(): Flow<NetworkResult<List<ProductCollection>>> =
        repository.getList().combine(repository.getFavoriteList()) { networkResult, products ->
            when (networkResult) {
                is NetworkResult.Loading -> NetworkResult.Loading
                is NetworkResult.Success -> NetworkResult.Success(modifyItems(networkResult.data, products))
                is NetworkResult.Failure -> NetworkResult.Failure(networkResult.throwable)
            }
        }

    private fun modifyItems(goods: Goods, list: List<Product>): List<ProductCollection> {
        val categoryMap: HashMap<String, MutableList<Product>> = hashMapOf()
        goods.categories.forEach {
            categoryMap[it.key] = mutableListOf()
        }
        goods.products.forEach { product ->
            categoryMap[product.category]?.add(product.copy(favorite = list.any { it.key == product.key }))
        }
        return categoryMap.map { entry ->
            val category = goods.categories.first { it.key == entry.key }
            ProductCollection(
                key = category.key,
                name = category.name,
                orderKey = category.order,
                products = entry.value
            )
        }
    }
}
