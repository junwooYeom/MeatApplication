package com.junwooyeom.meatapplication.di

import com.junwooyeom.meatapplication.data.repository.ProductRepositoryImpl
import com.junwooyeom.meatapplication.domain.repository.ProductRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @Binds
    @Singleton
    fun bindsProductRepository(impl: ProductRepositoryImpl): ProductRepository

}
