package com.example.recipeapp.recipes.repo

import androidx.lifecycle.LiveData
import com.example.recipeapp.models.Recipe
import com.example.recipeapp.models.RecipeResponse
import retrofit2.Response

interface RecipesRepository {
    suspend fun getRemoteRecipes() : Response<RecipeResponse>
    suspend fun getFavorites(userId: Int) : List<Recipe>
    suspend fun insertRecipe(recipe: Recipe)
}