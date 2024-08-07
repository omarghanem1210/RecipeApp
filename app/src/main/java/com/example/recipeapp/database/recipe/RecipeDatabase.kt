package com.example.recipeapp.database.recipe

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.recipeapp.models.Recipe

@Database(entities = [Recipe::class], version = 1)
abstract class RecipeDatabase: RoomDatabase() {
    abstract fun getProductDao(): RecipeDao
    companion object{
        private var instance:RecipeDatabase? = null
        @Synchronized
        fun getInstance(context: Context): RecipeDatabase? {
            synchronized(this) {
                if (instance == null)
                    instance = Room.databaseBuilder(
                        context.applicationContext, RecipeDatabase::class.java, "RecipeDatabase"
                    ).build()
            }
            return instance
        }
    }

}
