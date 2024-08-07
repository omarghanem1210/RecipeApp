package com.example.recipeapp.recipes.repo

import androidx.lifecycle.LiveData
import com.example.recipeapp.database.recipe.LocalRecipe
import com.example.recipeapp.database.recipe.LocalRecipeInterface
import com.example.recipeapp.models.Recipe
import com.example.recipeapp.models.RecipeResponse
import com.example.recipeapp.network.RemoteDataSource
import retrofit2.Response

class RecipesRepositoryImplementation(private var localDataSource: LocalRecipeInterface, private var remoteDataSource: RemoteDataSource) : RecipesRepository{
    override suspend fun getRemoteRecipes(): Response<RecipeResponse> {
        return remoteDataSource.getRecipeResponse()
    }

    override suspend fun getFavorites(userId: Int): List<Recipe> {
        return localDataSource.getFavorites(userId)
    }

    override suspend fun insertRecipe(recipe: Recipe) {
        localDataSource.insertRecipe(recipe)
    }
}