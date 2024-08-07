package com.example.recipeapp.network

import com.example.recipeapp.models.RecipeResponse
import retrofit2.Response

interface RemoteDataSource {
    suspend fun getRecipeResponse(): Response<RecipeResponse>
}