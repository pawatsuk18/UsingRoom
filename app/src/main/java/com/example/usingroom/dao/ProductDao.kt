package com.example.usingroom.dao

import androidx.room.*
import androidx.room.OnConflictStrategy.REPLACE
import com.example.usingroom.model.Product

@Dao
interface ProductDao {

    @Insert(onConflict = REPLACE)
    fun add(p:Product)

    @Query("SELECT * FROM Product ORDER BY ProductName")
    fun read() : List<Product>

    @Update
    fun update(p:Product)

    @Delete
    fun delete(p:Product)
}