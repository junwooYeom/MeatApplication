package com.junwooyeom.meatapplication.di

import android.content.Context
import androidx.room.Room
import com.junwooyeom.meatapplication.data.database.ApplicationDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Provides
    @Singleton
    fun providesDatabase(
        @ApplicationContext context: Context
    ): ApplicationDatabase = Room.databaseBuilder(
        context,
        ApplicationDatabase::class.java,
        "database-private.db"
    ).build()

    @Provides
    @Singleton
    fun providesFavoriteDao(applicationDatabase: ApplicationDatabase) = applicationDatabase.favoriteDao()
}
