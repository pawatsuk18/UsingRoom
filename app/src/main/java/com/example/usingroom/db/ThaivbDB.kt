package com.example.usingroom.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.usingroom.dao.ProductDao
import com.example.usingroom.model.Product

@Database(entities = [Product::class], version = 1, exportSchema = false)
abstract class ThaivbDB : RoomDatabase() {
    abstract fun productDao() : ProductDao

    companion object{
        @Volatile
        private var INSTANCE : ThaivbDB? = null
        private var DBNAME = "thaivb.db"

        fun getDatebase(context:Context) : ThaivbDB{
            val inst = INSTANCE
            if (inst != null){
                return inst
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ThaivbDB::class.java,
                    DBNAME
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}