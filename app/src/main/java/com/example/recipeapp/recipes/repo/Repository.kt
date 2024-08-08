package com.example.recipeapp.recipes.repo


import android.util.Log
import com.example.recipeapp.SearchResponse
import com.example.recipeapp.network.APIService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object Repository {
    private val retrofit = Retrofit.Builder()
        .baseUrl("https://www.themealdb.com/api.php")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val api = retrofit.create(APIService::class.java)

    fun search(query: String, callback: Callback<SearchResponse>) {
        api.search(query).enqueue(object : Callback<SearchResponse> {
            override fun onResponse(call: Call<SearchResponse>, response: Response<SearchResponse>) {
                if (response.isSuccessful) {
                    // Log response for debugging
                    Log.d("API Response", "Success: ${response.body()}")
                    callback.onResponse(call, response)
                } else {
                    // Log error for debugging
                    Log.e("API Response", "Error: ${response.errorBody()}")
                    callback.onFailure(call, Throwable("Error response"))
                }
            }

            override fun onFailure(call: Call<SearchResponse>, t: Throwable) {
                // Log failure for debugging
                Log.e("API Response", "Failure: ${t.message}")
                callback.onFailure(call, t)
            }
        })
    }

}
