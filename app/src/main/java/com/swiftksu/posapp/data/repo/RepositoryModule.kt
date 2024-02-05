package com.swiftksu.posapp.data.repo

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.mariya.flipkarttest.data.source.local.roomdb.dao.TransactionDao
import com.swiftksu.posapp.data.roomdb.db.TransactionDatabase
import com.swiftksu.posapp.domain.model.PriceBookRepoImpl
import com.swiftksu.posapp.domain.model.TransactionRepoImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Provides
    @Singleton
    fun providePriceBookRepository(@ApplicationContext context : Context):
            PriceBookRepository = PriceBookRepoImpl(context)

    @Provides
    @Singleton
    fun provideTransactionRepository(transactionDao: TransactionDao): TransactionRepository {
        return TransactionRepoImpl(transactionDao)
    }

    @Provides
    @Singleton
    fun provideRoomDatabase(application: Application): TransactionDatabase {
        return Room.databaseBuilder(
            application.applicationContext,
            TransactionDatabase::class.java,
            "transaction_database"
        ).build()
    }

    @Provides
    @Singleton
    fun provideTransactionDao(database: TransactionDatabase): TransactionDao {
        return database.transactionDao()
    }
}