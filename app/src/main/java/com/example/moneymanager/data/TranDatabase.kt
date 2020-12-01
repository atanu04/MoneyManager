package com.example.moneymanager.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.moneymanager.model.Tran

@Database(entities = [Tran::class],version = 1,exportSchema = false)
abstract class TranDatabase :RoomDatabase() {
    abstract fun tranDao() : TranDao

    companion object{
        private var INSTANCE: TranDatabase? = null

        fun getDatabase(context : Context) : TranDatabase{
            val temIns = INSTANCE
            if(temIns != null){
                return temIns
            }
            synchronized(this){
                val instance = Room.databaseBuilder(context.applicationContext,
                    TranDatabase::class.java,
                    "transaction_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }

}