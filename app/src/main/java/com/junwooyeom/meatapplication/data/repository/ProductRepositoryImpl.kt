package com.junwooyeom.meatapplication.data.repository

import com.junwooyeom.meatapplication.data.database.dao.FavoriteDao
import com.junwooyeom.meatapplication.data.network.ProductApi
import com.junwooyeom.meatapplication.data.mapper.toGoods
import com.junwooyeom.meatapplication.data.mapper.toProduct
import com.junwooyeom.meatapplication.data.mapper.toProductEntity
import com.junwooyeom.meatapplication.domain.model.NetworkResult
import com.junwooyeom.meatapplication.domain.repository.ProductRepository
import com.junwooyeom.meatapplication.domain.model.Goods
import com.junwooyeom.meatapplication.domain.model.Product
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ProductRepositoryImpl @Inject constructor(
    private val api: ProductApi,
    private val dao: FavoriteDao
) : ProductRepository {
    private var _productList: List<Product> = emptyList()
    override val productList: List<Product>
        get() = _productList

    override fun getList(): Flow<NetworkResult<Goods>> = flow {
        emit(NetworkResult.Loading)
        val data = api.getGoods().toGoods().also {
            _productList = it.products
        }
        emit(NetworkResult.Success(data))
    }.catch { throwable ->
        emit(NetworkResult.Failure(throwable))
    }

    override fun getFavoriteList(): Flow<List<Product>> =
        dao.getAllProductsByFlow().map { data -> data.map { it.toProduct() } }

    override suspend fun insertProduct(product: Product) = dao.insertProduct(product.toProductEntity())

    override suspend fun deleteProductById(key: String) = dao.deleteProduct(key)
}

