package com.example.recipeapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit
import androidx.lifecycle.lifecycleScope
import com.example.recipeapp.database.recipe.LocalRecipe
import com.example.recipeapp.database.recipe.LocalRecipeInterface
import com.example.recipeapp.network.APIClient
import com.example.recipeapp.network.RemoteDataSource
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    lateinit var localDataSource: LocalRecipeInterface
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity)
        localDataSource = LocalRecipe.getInstance(this)
        lifecycleScope.launch {
            var response = APIClient.getRecipeResponse()
            var results = response.body()?.meals
            if (results != null) {
                println(results.get(1))
            }

        }

    }
}
