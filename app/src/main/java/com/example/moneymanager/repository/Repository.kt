package com.example.moneymanager.repository

import androidx.lifecycle.LiveData
import com.example.moneymanager.data.TranDao
import com.example.moneymanager.model.Tran

class Repository(private val tranDao: TranDao) {

    val allTransaction: LiveData<List<Tran>> = tranDao.allTransaction()

    suspend fun addTransaction(tran: Tran){
        tranDao.addTransaction(tran)
    }
    fun deleteAllTransaction(){
        tranDao.deleteAllTransaction()
    }
    suspend fun deleteTran(tran: Tran){
        tranDao.deleteTran(tran)
    }
}