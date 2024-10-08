package com.example.recipeapp.recipes.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.recipeapp.recipes.repo.RecipesRepository

class RecipeViewModelFactory(var recipesRepository: RecipesRepository): ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return RecipeViewModel(recipesRepository) as T
    }


}