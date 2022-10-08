package com.junwooyeom.meatapplication.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.junwooyeom.meatapplication.data.database.dao.FavoriteDao
import com.junwooyeom.meatapplication.data.database.model.ProductEntity

@Database(
    entities = [ProductEntity::class],
    version = 1,
    exportSchema = false
)
abstract class ApplicationDatabase : RoomDatabase(){
    abstract fun favoriteDao(): FavoriteDao
}
