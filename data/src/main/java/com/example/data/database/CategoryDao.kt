package com.example.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.domain.model.Category


@Dao
interface CategoryDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveCategory(category: List<Category>)

    @Query("SELECT * FROM  Category ")
    suspend fun getCategory():List<Category>
}