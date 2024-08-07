package com.example.recipeapp.recipes.repo

import androidx.lifecycle.LiveData
import com.example.recipeapp.models.Recipe

interface RecipesRepository {
    suspend fun getLocalRecipes() : LiveData<List<Recipe>>
    suspend fun insertRecipe(recipe: Recipe)
}