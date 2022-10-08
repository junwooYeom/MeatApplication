package com.junwooyeom.meatapplication.presentation.view

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.junwooyeom.meatapplication.domain.model.NetworkResult
import com.junwooyeom.meatapplication.domain.model.Product
import com.junwooyeom.meatapplication.domain.model.ProductCollection
import com.junwooyeom.meatapplication.presentation.component.product.ProductCollection
import com.junwooyeom.meatapplication.presentation.viewmodel.ProductViewModel

@Composable
fun ProductList(
    onProductClick: (Product) -> Unit,
    modifier: Modifier = Modifier,
    productViewModel: ProductViewModel = hiltViewModel()
) {
    val productCollection = productViewModel.viewItems.collectAsState()
    Column {
        Text(text = "식품 리스트")
        when (productCollection.value) {
            is NetworkResult.Loading -> ProductLoading(modifier = modifier)
            is NetworkResult.Success -> {
                ProductList(
                    collections = (productCollection.value as NetworkResult.Success<List<ProductCollection>>).data,
                    onProductClick = onProductClick,
                    onProductFavoriteClick = { item, isFavorite ->
                        if (isFavorite) productViewModel.insertItemToDatabase(item)
                        else productViewModel.deleteItemToDataBase(item.key)
                    },
                    modifier = modifier
                )
            }
            is NetworkResult.Failure -> ProductFailure(modifier = modifier, productViewModel = productViewModel)
        }
    }
}

@Composable
fun ProductList(
    collections: List<ProductCollection>,
    onProductClick: (Product) -> Unit,
    onProductFavoriteClick: (Product, Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(modifier = modifier.fillMaxSize()) {
        LazyColumn {
            items(collections) {
                ProductCollection(
                    productCollection = it,
                    onProductClick = onProductClick,
                    onFavoriteClick = onProductFavoriteClick
                )
            }
        }
    }
}

@Composable
fun ProductLoading(
    modifier: Modifier
) {
    Surface(modifier = modifier.fillMaxSize()) {
        Box(contentAlignment = Alignment.Center) {
            CircularProgressIndicator(
                color = MaterialTheme.colors.primary
            )
        }
    }
}

@Composable
fun ProductFailure(
    modifier: Modifier = Modifier,
    productViewModel: ProductViewModel = hiltViewModel()
) {
    Surface(modifier = modifier.fillMaxSize()) {
        Box {
            Column(
                modifier = modifier
                    .align(Alignment.Center)
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = "굿즈 불러오기에 실패하였습니다.\n다시 한번 시도해주세요.", textAlign = TextAlign.Center)
                Button(onClick = { productViewModel.refresh() }) {
                    Text(text = "다시 부르기")
                }
            }
        }
        Toast.makeText(LocalContext.current, "불러오기에 실패하였습니다", Toast.LENGTH_SHORT).show()
    }
}

