package com.example.recipeapp.users.view

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.navigation.fragment.NavHostFragment
import com.example.recipeapp.R
import com.example.recipeapp.models.User
import com.example.recipeapp.models.UserManager
import com.example.recipeapp.recipes.view.MainActivity
import com.google.gson.Gson

class SplashActivity : AppCompatActivity() {
    lateinit var sharedPreferences: SharedPreferences
    private val gson = Gson()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        Handler(Looper.getMainLooper()).postDelayed({
            sharedPreferences = getSharedPreferences("user_prefs", Context.MODE_PRIVATE)
            val user = getUser()

            if(user != null){
                UserManager.currentUser = user
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)

            }
            else{
                val intent2 =  Intent(this, UserActivity::class.java)
                startActivity(intent2)

            }
        }, 3000)







    }

    fun getUser(): User? {
        val json = sharedPreferences.getString("logged", null)
        return json?.let { gson.fromJson(it, User::class.java) }
    }


}
