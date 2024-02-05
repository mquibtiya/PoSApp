package com.mariya.flipkarttest.data.source.local.roomdb.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.swiftksu.posapp.data.roomdb.entity.TransactionEntity

@Dao
interface TransactionDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveTransaction(transaction: TransactionEntity)

    @Query("DELETE FROM transactions WHERE txnStatus = 'SAVED'")
    suspend fun deleteEarlierSavedTransactions()

    @Query("SELECT * FROM transactions WHERE txnStatus = 'SAVED' ORDER BY txnStartTime DESC LIMIT 1")
    suspend fun recallLastSavedTransaction(): TransactionEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addCompletedTransaction(transaction: TransactionEntity)

    @Query("SELECT * FROM transactions WHERE txnStatus = 'COMPLETED' ORDER BY txnStartTime DESC")
    suspend fun getAllTransactionsHistory(): List<TransactionEntity>
}
