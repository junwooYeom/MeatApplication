package com.junwooyeom.meatapplication.domain.usecase

import com.junwooyeom.meatapplication.domain.repository.ProductRepository
import com.junwooyeom.meatapplication.domain.model.Product
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
class InsertProductUseCase @Inject constructor(
    private val repository: ProductRepository
){
    suspend operator fun invoke(product: Product) = repository.insertProduct(product)
}
