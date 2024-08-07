package com.example.recipeapp.database.recipe

import android.content.Context
import androidx.lifecycle.LiveData
import com.example.recipeapp.models.Recipe

class LocalRecipe private constructor(context: Context) : LocalRecipeInterface {
    private lateinit var recipeDao: RecipeDao
    init {
        val recipeDatabase = RecipeDatabase.getInstance(context.applicationContext)
        if (recipeDatabase != null){
            recipeDao = recipeDatabase.getRecipeDao()
        }
    }
    companion object {
        var instance: LocalRecipe? = null
        fun getInstance(context: Context): LocalRecipeInterface {
            if (instance == null) {
                instance = LocalRecipe(context)
            }
            return instance as LocalRecipe
        }
    }

    override fun getAllRecipes(): LiveData<List<Recipe>> {
        return recipeDao.getAllRecipes()
    }

    override suspend fun insertRecipe(quote: Recipe) {
        recipeDao.insertRecipe(quote);
    }

    override suspend fun deleteRecipe(quote: Recipe) {
        recipeDao.deleteRecipe(quote)
    }
}
