package com.example.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.data.model.CategoryDto
import com.example.domain.model.Category

@Database(entities = [Category::class], version = 1)
abstract class MealsDataBase : RoomDatabase() {
 abstract fun getCategoryDao(): CategoryDao

    companion object{
        private var INCTANCE:MealsDataBase?=null
        private val DATABASE_NAME="news database"
        fun init(context: Context){
            if (INCTANCE==null){
                INCTANCE= Room.databaseBuilder(context, MealsDataBase::class.java, DATABASE_NAME)
                    .fallbackToDestructiveMigration()
                    .build()
            }
        }
        fun getInctance():MealsDataBase{
            return INCTANCE!!
        }

    }
}