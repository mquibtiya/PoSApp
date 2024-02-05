package com.swiftksu.posapp.domain.model

import com.mariya.flipkarttest.data.source.local.roomdb.dao.TransactionDao
import com.swiftksu.posapp.data.repo.TransactionRepository
import com.swiftksu.posapp.data.roomdb.entity.TransactionEntity
import com.swiftksu.posapp.data.roomdb.entity.TransactionItemEntity
import com.swiftksu.posapp.domain.dto.Transaction
import com.swiftksu.posapp.domain.dto.TransactionItem
import javax.inject.Inject

// Data layer
class TransactionRepoImpl(
    private val transactionDao: TransactionDao
) : TransactionRepository {

    override suspend fun saveTransaction(transaction: Transaction) {
        val transactionEntity = transaction.toEntity()
        transactionDao.saveTransaction(transactionEntity)

        // Save transaction items
        transaction.txnItems.forEach {
            val transactionItemEntity = it.toEntity(transaction.txnId)
//            transactionDao.addTransactionItem(transactionItemEntity)
        }
    }

    override suspend fun deleteEarlierSavedTransactions() {
        transactionDao.deleteEarlierSavedTransactions()
    }

    override suspend fun recallLastSavedTransaction(): Transaction? {
        val entity = transactionDao.recallLastSavedTransaction()
        return entity?.toTransactionItemDto()
    }

    override suspend fun addCompletedTransaction(transaction: Transaction) {
        val transactionEntity = transaction.toEntity()
        transactionDao.addCompletedTransaction(transactionEntity)

        // Save transaction items
        transaction.txnItems.forEach {
            val transactionItemEntity = it.toEntity(transaction.txnId)
//            transactionDao.addTransactionItem(transactionItemEntity)
        }
    }

    override suspend fun getAllTransactionsHistory(): List<Transaction> {
        val entities = transactionDao.getAllTransactionsHistory()
        return entities.map { it.toTransactionItemDto() }
    }

    private fun Transaction.toEntity(): TransactionEntity {
        return TransactionEntity(
            txnId,
            txnItems,
            txnEndTime,
            txnTotalGrandAmount,
            txnTotalDiscountAmount,
            txnTotalTaxAmount,
            txnSubTotalAmount,
            txnStatus,
            txnStartTime
        )
    }

    private fun TransactionItem.toEntity(txnId: String): TransactionItemEntity {
        return TransactionItemEntity(
            txnId = txnId,
            pluId = pluItem.pluId,
            quantity = quantity
        )
    }
}
