package com.example.recipeapp.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Recipe")
data class Recipe(
    @PrimaryKey val id: Int,
)
