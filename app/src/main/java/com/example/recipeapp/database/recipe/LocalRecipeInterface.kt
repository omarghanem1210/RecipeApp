package com.example.recipeapp.database.recipe

import androidx.lifecycle.LiveData
import com.example.recipeapp.models.Recipe

interface LocalRecipeInterface{

    fun getFavorites(userName: String): List<Recipe>
    suspend fun insertRecipe(quote: Recipe)
    suspend fun deleteRecipe(quote: Recipe)
}