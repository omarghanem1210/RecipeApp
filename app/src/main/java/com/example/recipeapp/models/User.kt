package com.example.recipeapp.models

data class User(
        val username: String,
        val passwordHash: String,
    )


object UserManager {
    var currentUser: User? = null
}
