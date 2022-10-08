package com.junwooyeom.meatapplication.domain.usecase

import com.junwooyeom.meatapplication.domain.repository.ProductRepository
import com.junwooyeom.meatapplication.domain.model.Product
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
class GetDetailUseCase @Inject constructor(
    private val repository: ProductRepository
) {
    operator fun invoke(key: String): Product? = repository.productList.find { it.key == key }
}
