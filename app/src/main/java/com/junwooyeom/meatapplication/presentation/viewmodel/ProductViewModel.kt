package com.junwooyeom.meatapplication.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.junwooyeom.meatapplication.domain.usecase.DeleteProductUseCase
import com.junwooyeom.meatapplication.domain.usecase.GetDetailUseCase
import com.junwooyeom.meatapplication.domain.usecase.GetFavoritesUseCase
import com.junwooyeom.meatapplication.domain.usecase.GetProductsUseCase
import com.junwooyeom.meatapplication.domain.usecase.InsertProductUseCase
import com.junwooyeom.meatapplication.domain.model.NetworkResult
import com.junwooyeom.meatapplication.domain.usecase.RefreshUseCase
import com.junwooyeom.meatapplication.domain.model.Product
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductViewModel @Inject constructor(
    getProductsUseCase: GetProductsUseCase,
    private val refreshUseCase: RefreshUseCase,
    favoritesUseCase: GetFavoritesUseCase,
    private val insertProductUseCase: InsertProductUseCase,
    private val deleteProductUseCase: DeleteProductUseCase,
    private val getDetailUseCase: GetDetailUseCase
) : ViewModel() {

    val viewItems = getProductsUseCase()
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            initialValue = NetworkResult.Loading
        )

    private var filteredItemFlow: MutableStateFlow<String> = MutableStateFlow("")

    val favoriteItems = favoritesUseCase().combine(filteredItemFlow) { list, keyword ->
        list.filter { keyword.isEmpty() || it.name.contains(keyword) }
    }.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000),
        initialValue = emptyList()
    )

    fun selectProduct(key: String) : Product? = getDetailUseCase(key)

    fun refresh() = refreshUseCase()

    fun findFavoriteWithText(keyword: String) {
        viewModelScope.launch {
            filteredItemFlow.emit(keyword)
        }
    }

    fun insertItemToDatabase(product: Product) {
        viewModelScope.launch(Dispatchers.IO) {
            insertProductUseCase(product)
        }
    }

    fun deleteItemToDataBase(key: String) {
        viewModelScope.launch(Dispatchers.IO) {
            deleteProductUseCase(key)
        }
    }
}
