package com.junwooyeom.meatapplication.data.network

import com.junwooyeom.meatapplication.data.network.GoodsDto
import retrofit2.http.GET

interface ProductApi {
    @GET("products")
    suspend fun getGoods(): GoodsDto
}
