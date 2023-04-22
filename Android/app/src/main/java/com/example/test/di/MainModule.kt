package com.example.test.di

import android.content.Context
import androidx.room.Room
import com.eigthteentech.gapsi.entities.DB
import com.example.test.data.ProductDataSource
import com.example.test.data.ProductDataSourceImpl
import com.example.test.network.Api
import com.example.test.network.Network
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MainModule {
    @Provides
    @Singleton
    fun provideApi(): Api = Network.service

    @Provides
    @Singleton
    fun provideDataBase(@ApplicationContext appContext: Context): DB =
        Room.databaseBuilder(appContext, DB::class.java, "db").allowMainThreadQueries()
            .build()

    @Provides
    @Singleton
    fun provideProductDataSource(api: Api): ProductDataSource = ProductDataSourceImpl(api = api)


}