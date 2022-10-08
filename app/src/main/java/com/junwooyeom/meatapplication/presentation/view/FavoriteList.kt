package com.junwooyeom.meatapplication.presentation.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.junwooyeom.meatapplication.domain.model.Product
import com.junwooyeom.meatapplication.presentation.viewmodel.ProductViewModel
import com.junwooyeom.meatapplication.presentation.component.product.ProductVerticalListItem

@Composable
fun FavoriteList(
    onProductClick: (Product) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: ProductViewModel = hiltViewModel()
) {
    val favoriteItems = viewModel.favoriteItems.collectAsState()
    val localFocusManager = LocalFocusManager.current
    var keyword by remember { mutableStateOf("") }
    Surface(modifier = modifier.fillMaxSize()) {
        Column {
            Text(text = "찜 목록")
            OutlinedTextField(value = keyword, onValueChange = {
                viewModel.findFavoriteWithText(it)
                keyword = it
            }, label = { Text(text = "검색어를 입력해주세요.") },
                maxLines = 1,
                modifier = modifier
                    .padding(vertical = 8.dp, horizontal = 16.dp)
                    .fillMaxWidth(),
                textStyle = TextStyle(color = MaterialTheme.colors.surface, fontWeight = FontWeight.SemiBold),
                keyboardActions = KeyboardActions(onDone = { localFocusManager.clearFocus(true) }),
                keyboardOptions = KeyboardOptions.Default.copy(
                    imeAction = ImeAction.Done,
                    keyboardType = KeyboardType.Text
                )
            )

            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(16.dp),
                modifier = modifier.padding(horizontal = 16.dp)
            ) {
                items(favoriteItems.value) {
                    ProductVerticalListItem(
                        product = it,
                        onProductClick = onProductClick,
                        onProductFavoriteClick = { item, _ ->
                            viewModel.deleteItemToDataBase(item.key)
                        },
                        modifier = modifier
                    )
                }
            }
        }
    }
}
