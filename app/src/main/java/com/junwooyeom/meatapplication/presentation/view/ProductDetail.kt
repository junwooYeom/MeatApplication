package com.junwooyeom.meatapplication.presentation.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.IconToggleButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.junwooyeom.meatapplication.domain.model.Product
import com.junwooyeom.meatapplication.presentation.component.product.ProductImage
import com.junwooyeom.meatapplication.presentation.viewmodel.ProductViewModel

@Composable
fun ProductDetail(
    productKey: String,
    upPress: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: ProductViewModel = hiltViewModel()
) {
    val item = viewModel.selectProduct(productKey)
    Surface(modifier = modifier.padding(16.dp)){
        Column {
            IconButton(onClick = upPress) {
                Icon(imageVector = Icons.Filled.Close, contentDescription = null)
            }
            if (item != null) {
                ProductDetailLayout(product = item, onProductFavoriteClicked = { item, boolean ->
                    if (boolean) viewModel.insertItemToDatabase(item)
                    else viewModel.deleteItemToDataBase(item.key)
                })
            } else {
                ProductNotFoundView(upPress)
            }
        }
    }
}

@Composable
fun ProductDetailLayout(
    product: Product,
    onProductFavoriteClicked: (Product, Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    val isFavorite = remember { mutableStateOf(product.favorite) }
    Column {
        ProductImage(
            imageUrl = product.thumbnail,
            contentDescription = null,
            modifier = Modifier
                .width(120.dp)
                .height(120.dp)
        )

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = modifier
                .fillMaxWidth()
                .padding(
                    start = 8.dp,
                    bottom = 4.dp
                )
        ) {
            Column(
                modifier = modifier
                    .weight(1f)
                    .wrapContentWidth(Alignment.Start)
            ) {
                Text(
                    text = product.name,
                    color = MaterialTheme.colors.primary,
                    fontSize = 16.sp,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1
                )
                Text(
                    text = product.price.toString(),
                    modifier = modifier.padding(
                        top = 4.dp
                    ),
                    color = MaterialTheme.colors.secondary,
                    fontSize = 12.sp
                )
            }

            IconToggleButton(
                checked = isFavorite.value,
                onCheckedChange = {
                    onProductFavoriteClicked(product, it)
                    isFavorite.value = it },
                modifier = modifier.wrapContentWidth(Alignment.End)
            ) {
                val tint = MaterialTheme.colors.error
                if (isFavorite.value) {
                    Icon(
                        imageVector = Icons.Filled.Favorite,
                        contentDescription = "Favorite",
                        tint = tint
                    )
                } else {
                    Icon(
                        imageVector = Icons.Filled.FavoriteBorder,
                        contentDescription = "Not Favorite",
                        tint = tint
                    )
                }
            }
        }

    }
}

@Composable
fun ProductNotFoundView(
    upPress: () -> Unit
) {
    Text(text = "좌송합니다. 요청하신 정보를 찾을 수 없습니다. 뒤로 돌아가주세요.")
    Button(onClick = upPress) {
        Text(text = "뒤로 돌아가기")
    }
}
