package com.junwooyeom.meatapplication.presentation.component.product

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.junwooyeom.meatapplication.domain.model.Product
import com.junwooyeom.meatapplication.domain.model.ProductCollection
import com.junwooyeom.meatapplication.ui.theme.MeatApplicationTheme

@Composable
fun ProductCollection(
    productCollection: ProductCollection,
    onProductClick: (Product) -> Unit,
    onFavoriteClick: (Product, Boolean) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column {
        Text(text = productCollection.name, modifier = modifier.padding(
            start = 16.dp, top = 16.dp,
        ),
        fontSize = 22.sp,
        fontWeight = FontWeight.Bold
        )
        Product(products = productCollection.products, onProductClick = onProductClick, onProductFavoriteClick = onFavoriteClick, modifier = modifier)
    }
}

@Preview
@Composable
fun PreviewProductCollection() {
    MeatApplicationTheme {
        val collection = ProductCollection("pork", "돼지", orderKey = 1, products = listOf())
        ProductCollection(
            productCollection = collection,
            onProductClick = {},
            onFavoriteClick = {_, _ ->}
        )
    }
}
