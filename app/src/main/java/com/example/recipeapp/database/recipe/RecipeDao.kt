package com.example.recipeapp.database.recipe

import androidx.lifecycle.LiveData
import androidx.room.*
import androidx.room.Insert
import kotlinx.coroutines.flow.Flow
import com.example.recipeapp.models.Recipe

@Dao
interface RecipeDao {
    @Query("Select * from Recipe where idMeal = :userId")
    fun getFavorites(userId: Int): LiveData<List<Recipe>>

    @Insert
    suspend fun insertRecipe(recipe: Recipe)

    @Delete
    suspend fun deleteRecipe(recipe: Recipe)


}