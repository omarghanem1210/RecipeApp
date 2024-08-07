package com.example.recipeapp.network

import com.example.recipeapp.models.RecipeResponse
import retrofit2.Response
import retrofit2.http.GET

interface APIService {
    @GET("search.php?s=")
    suspend fun getAllRecipes(): Response<RecipeResponse>

}