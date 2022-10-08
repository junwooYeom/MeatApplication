package com.junwooyeom.meatapplication

import androidx.compose.material.Surface
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.junwooyeom.meatapplication.domain.model.Product
import com.junwooyeom.meatapplication.presentation.view.ProductDetailLayout
import com.junwooyeom.meatapplication.ui.theme.MeatApplicationTheme
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ProductDetailTests {

    private val product = Product(
        key = "port01",
        category = "pork",
        name = "목살",
        price = 16400,
        thumbnail = "",
        order = 0,
        favorite = true
    )

    @get:Rule
    val composeTestRule = createComposeRule()

    @Before
    fun setUp() {
        composeTestRule.setContent {
            MeatApplicationTheme {
                Surface {
                    ProductDetailLayout(product = product, onProductFavoriteClicked = {_, _ -> })
                }
            }
        }
    }

    @Test
    fun detailScreen_detailDisplayed() {
        composeTestRule.onNodeWithText(product.name).assertIsDisplayed()
        composeTestRule.onNodeWithText(product.price.toString()).assertIsDisplayed()
    }
}
