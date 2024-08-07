package com.example.recipeapp.network

import com.example.recipeapp.models.RecipeResponse
import com.example.recipeapp.recipes.repo.RetrofitService
import retrofit2.Response

object APIClient: RemoteDataSource {
    override suspend fun getRecipeResponse(): Response<RecipeResponse> {
        return RetrofitService.retrofit.create(APIService::class.java).getAllRecipes()
    }
}