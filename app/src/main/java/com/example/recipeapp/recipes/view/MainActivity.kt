package com.example.recipeapp.recipes.view

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI.setupWithNavController
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.recipeapp.R
import com.example.recipeapp.about_usFragment
import com.example.recipeapp.users.view.UserActivity
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    lateinit var navController: NavController
    lateinit var toolbar: Toolbar
    lateinit var sharedPreferences: SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

        sharedPreferences = getSharedPreferences("user_prefs", Context.MODE_PRIVATE)!!

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host) as NavHostFragment
        navController = navHostFragment.navController
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_nav)
        setupWithNavController(bottomNavigationView, navController)
        Log.i("Result", "MainNavigate : ")



        bottomNavigationView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.homeFragment -> {
                    // Always navigate to the HomeFragment, clearing the back stack
                    navController.popBackStack(R.id.homeFragment, false)
                    navController.navigate(R.id.homeFragment)
                    true
                }

                R.id.recipeDetailFragment -> {
                    navController.navigate(R.id.recipeDetailFragment)
                    true
                }

                R.id.favouriteFragment -> {
                    navController.navigate(R.id.favouriteFragment)
                    true
                }

                else -> false
            }
        }

        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.title = "RecipeApp"
       


    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.app_bar, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_about_us -> {
                navController.navigate(R.id.about_usFragment)
                return true
            }
            R.id.action_logout -> {
                deleteUser()
                val intent = Intent(this, UserActivity::class.java)
                intent.flags = (Intent.FLAG_ACTIVITY_NEW_TASK
                        or Intent.FLAG_ACTIVITY_CLEAR_TASK);
                this.startActivity(intent)
            }


        }
        return super.onOptionsItemSelected(item)
    }
    fun deleteUser() {
        val editor = sharedPreferences.edit()
        editor.remove("logged")
        editor.apply()
    }

}


