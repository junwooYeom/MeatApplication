package com.junwooyeom.meatapplication.domain.usecase

import com.junwooyeom.meatapplication.domain.repository.ProductRepository
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
class RefreshUseCase @Inject constructor(
    private val repository: ProductRepository
) {
    operator fun invoke() { repository.getList() }
}
