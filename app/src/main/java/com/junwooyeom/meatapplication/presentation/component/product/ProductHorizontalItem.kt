package com.junwooyeom.meatapplication.presentation.component.product

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconToggleButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.junwooyeom.meatapplication.domain.model.Product
import com.junwooyeom.meatapplication.ui.theme.MeatApplicationTheme

@Composable
fun Product(
    products: List<Product>,
    onProductClick: (Product) -> Unit,
    onProductFavoriteClick: (Product, Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyRow(
        modifier = modifier,
        contentPadding = PaddingValues(horizontal = 12.dp)
    ) {
        items(products) { product ->
            ProductHorizontalGridItem(
                product = product,
                onProductClick =
                onProductClick,
                onProductFavoriteClick = onProductFavoriteClick
            )
        }
    }
}

@Composable
fun ProductHorizontalGridItem(
    product: Product,
    onProductClick: (Product) -> Unit,
    onProductFavoriteClick: (Product, Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    val isFavorite = remember { mutableStateOf(product.favorite) }
    Surface(
        shape = MaterialTheme.shapes.medium,
        modifier = modifier
            .width(120.dp)
            .padding(
                4.dp
            )
            .border(2.dp, MaterialTheme.colors.primary, RoundedCornerShape(12.dp))
            .clickable(onClick = { onProductClick(product) })
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier

        ) {
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
                    modifier = modifier.weight(1f).wrapContentWidth(Alignment.Start)
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
                    onProductFavoriteClick(product, it)
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
}

@Composable
fun ProductImage(
    imageUrl: String,
    contentDescription: String?,
    modifier: Modifier = Modifier,
    elevation: Dp = 0.dp,
) {
    Surface(
        elevation = elevation,
        modifier = modifier
    ) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(imageUrl)
                .crossfade(true)
                .build(),
            contentDescription = contentDescription,
            modifier = Modifier,
            contentScale = ContentScale.Crop
        )
    }
}

@Preview("default")
@Preview("dark theme", uiMode = UI_MODE_NIGHT_YES)
@Preview("large font", fontScale = 2f)
@Composable
fun ProductCardPreview() {
    MeatApplicationTheme {
        val product = Product(
            key = "pork01",
            category = "pork",
            name = "목살",
            thumbnail = "https://android-test.yookgak.com/static/JeongyookgakLogo.png",
            price = 17600,
            order = 2,
            favorite = true
        )
        ProductHorizontalGridItem(product = product, onProductClick = {}, onProductFavoriteClick = {_, _ -> })
    }
}
