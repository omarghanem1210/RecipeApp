package com.example.recipeapp.models

import com.google.gson.annotations.SerializedName

data class RecipeResponse(
    @SerializedName("meals") val meals: List<Recipe>
)
