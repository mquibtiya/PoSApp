package com.swiftksu.posapp.data.repo

import com.swiftksu.posapp.domain.dto.Transaction

interface TransactionRepository {
    suspend fun saveTransaction(transaction: Transaction)
    suspend fun deleteEarlierSavedTransactions()
    suspend fun recallLastSavedTransaction(): Transaction?
    suspend fun addCompletedTransaction(transaction: Transaction)
    suspend fun getAllTransactionsHistory(): List<Transaction>
}