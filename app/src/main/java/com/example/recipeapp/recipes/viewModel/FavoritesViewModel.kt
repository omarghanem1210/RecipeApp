package com.example.recipeapp.recipes.viewModel


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recipeapp.models.Recipe
import com.example.recipeapp.recipes.repo.RecipesRepository
import kotlinx.coroutines.launch

class FavoritesViewModel(var recipesRepository: RecipesRepository): ViewModel() {


    private var _favorites = MutableLiveData<List<Recipe>>()
    val favorites: LiveData<List<Recipe>> = _favorites


    fun getFavorites(userName: String) {
        viewModelScope.launch {
            _favorites.value = recipesRepository.getFavorites(userName)
        }
    }

}