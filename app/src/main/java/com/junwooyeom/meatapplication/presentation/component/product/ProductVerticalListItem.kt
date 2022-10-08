package com.junwooyeom.meatapplication.presentation.component.product

import android.content.res.Configuration
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
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
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.junwooyeom.meatapplication.domain.model.Product
import com.junwooyeom.meatapplication.ui.theme.MeatApplicationTheme

@Composable
fun ProductVerticalListItem(
    product: Product,
    onProductClick: (Product) -> Unit,
    onProductFavoriteClick: (Product, Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(modifier = modifier
        .fillMaxWidth()
        .border(width = 2.dp, MaterialTheme.colors.primary, RoundedCornerShape(12.dp))
        .padding(16.dp)
        .clickable { onProductClick(product) }) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            ProductImage(
                imageUrl = product.thumbnail,
                contentDescription = null,
                modifier = modifier
                    .width(60.dp)
                    .height(80.dp),
            )
            Column(
                modifier = modifier.weight(2f).wrapContentWidth(Alignment.Start).height(IntrinsicSize.Max)
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
                checked = product.favorite,
                onCheckedChange = { onProductFavoriteClick(product, it) },
                modifier = modifier.wrapContentWidth(Alignment.End)
            ) {
                val tint = MaterialTheme.colors.error
                if (product.favorite) {
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

@Preview("default")
@Preview("dark theme", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Preview("large font", fontScale = 2f)
@Composable
fun ProductVerticalListPreview() {
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
        ProductVerticalListItem(product = product, onProductClick = {}, onProductFavoriteClick = {_, _ -> })
    }
}

