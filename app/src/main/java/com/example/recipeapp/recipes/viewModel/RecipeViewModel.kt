package com.example.recipeapp.recipes.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recipeapp.models.Recipe
import com.example.recipeapp.models.RecipeResponse
import com.example.recipeapp.recipes.repo.RecipesRepository
import kotlinx.coroutines.launch
import retrofit2.Response

class RecipeViewModel(var recipesRepository: RecipesRepository): ViewModel() {

    private var _allRecipes = MutableLiveData<List<Recipe>>()
    val allRecipes: LiveData<List<Recipe>> = _allRecipes




    fun getRecipes() {
        viewModelScope.launch {
            val apiResponse: Response<RecipeResponse> = recipesRepository.getRemoteRecipes()
            if (apiResponse.isSuccessful) {
                _allRecipes.value = apiResponse.body()?.meals
            }


        }

    }

    fun getSearchRecipes(searchString: String){
        if(searchString.isEmpty()){
            getRecipes()
        }
        else{
            val unFilteredList = _allRecipes.value ?: emptyList()
            val filteredList = unFilteredList.filter { recipe ->
                recipe.strMeal?.contains(searchString, ignoreCase = true) ?: false
            }
                _allRecipes.value = filteredList

        }

    }
}