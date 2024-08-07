package com.example.recipeapp.recipes.repo

import androidx.lifecycle.LiveData
import com.example.recipeapp.database.recipe.LocalRecipe
import com.example.recipeapp.models.Recipe

class RecipesRepositoryImplementation(private var localDataSource: LocalRecipe) : RecipesRepository{

    override suspend fun getLocalRecipes(): LiveData<List<Recipe>> {
        return localDataSource.getAllRecipes()
    }

    override suspend fun insertRecipe(recipe: Recipe) {
        localDataSource.insertRecipe(recipe)
    }
}