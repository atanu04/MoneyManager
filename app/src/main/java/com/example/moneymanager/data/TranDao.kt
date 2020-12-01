package com.example.moneymanager.data

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.moneymanager.model.Tran


@Dao
interface TranDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addTransaction(tran : Tran)

    @Query("SELECT * FROM transaction_table ORDER BY id ASC")
    fun allTransaction(): LiveData<List<Tran>>

    @Query("DELETE FROM transaction_table")
    fun deleteAllTransaction()

    @Delete
    fun deleteTran(tran: Tran)
}