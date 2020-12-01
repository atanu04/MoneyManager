package com.example.moneymanager.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "transaction_table")
data class Tran (

    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val amount: Int,
    val type: Char,
    val desc: String,
    val date: String
)
