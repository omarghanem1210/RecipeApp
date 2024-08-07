package com.example.recipeapp.database.recipe

import android.content.Context
import androidx.lifecycle.LiveData
import com.example.recipeapp.models.Recipe

class LocalRecipe private constructor(context: Context) : LocalRecipeInterface {
    private lateinit var recipeDao: RecipeDao
    init {
        val recipeDatabase = RecipeDatabase.getInstance(context.applicationContext)
        if (recipeDatabase != null){
            recipeDao = recipeDatabase.getProductDao()
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
        return recipeDao.getAllProducts()
    }

    override suspend fun insertRecipe(quote: Recipe) {
        recipeDao.insertProduct(quote);
    }

    override suspend fun deleteRecipe(quote: Recipe) {
        recipeDao.deleteProduct(quote)
    }
}
