package com.junwooyeom.meatapplication.domain.usecase

import com.junwooyeom.meatapplication.domain.repository.ProductRepository
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
class DeleteProductUseCase @Inject constructor(
    private val repository: ProductRepository
) {
    suspend operator fun invoke(key: String) = repository.deleteProductById(key)
}
