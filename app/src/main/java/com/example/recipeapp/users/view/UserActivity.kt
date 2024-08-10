package com.example.recipeapp.users.view


import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI.setupWithNavController
import com.example.recipeapp.R
import com.example.recipeapp.databinding.ActivityMainBinding
import com.example.recipeapp.models.User
import com.example.recipeapp.models.UserManager
import com.example.recipeapp.recipes.view.MainActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.gson.Gson

class UserActivity : AppCompatActivity() {
    lateinit var sharedPreferences: SharedPreferences
    private val gson = Gson()

    override fun onCreate(savedInstanceState: Bundle?) {

        sharedPreferences = getSharedPreferences("user_prefs",Context.MODE_PRIVATE)
        val user = getUser()

        if(user != null){
            UserManager.currentUser = user
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)

        }

        super.onCreate(savedInstanceState)
//        binding =ActivityMainBinding.inflate((layoutInflater))
//        setContentView(binding.root)
        setContentView(R.layout.activity_user)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)


        val navHostFragment= supportFragmentManager.findFragmentById(R.id.nav_host) as NavHostFragment



    }

    fun getUser(): User? {
        val json = sharedPreferences.getString("logged", null)
        return json?.let { gson.fromJson(it, User::class.java) }
    }



}

