package com.junwooyeom.meatapplication.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.junwooyeom.meatapplication.data.database.model.ProductEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteDao {

    @Query("SELECT * FROM ProductEntity")
    fun getAllProductsByFlow(): Flow<List<ProductEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProduct(product: ProductEntity)

    @Query("DELETE FROM ProductEntity WHERE `key` = :key")
    suspend fun deleteProduct(key: String)
}
