package com.example.moneymanager.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.moneymanager.data.TranDatabase
import com.example.moneymanager.model.Tran
import com.example.moneymanager.repository.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TranViewModel(application: Application): AndroidViewModel(application) {

    val allTransaction : LiveData<List<Tran>>
    private val repository: Repository

    init {
        val tranDao = TranDatabase.getDatabase(application).tranDao()
        repository = Repository(tranDao)
        allTransaction = repository.allTransaction
    }

    fun addTransaction(tran: Tran){
        viewModelScope.launch(Dispatchers.IO){
            repository.addTransaction(tran)
        }
    }

    fun deleteAllTransaction(){
        viewModelScope.launch(Dispatchers.IO){
            repository.deleteAllTransaction()
        }
    }

    fun deleteTran(tran: Tran){
        viewModelScope.launch (Dispatchers.IO){
            repository.deleteTran(tran)
        }
    }


}