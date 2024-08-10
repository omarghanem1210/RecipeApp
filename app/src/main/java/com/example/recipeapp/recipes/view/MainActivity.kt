package com.example.recipeapp.recipes.view

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
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    lateinit var navController: NavController
    lateinit var toolbar: Toolbar
    lateinit var drawerLayout: DrawerLayout
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)


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
        supportActionBar?.title = "RecipeAPP"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)


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
                navController.navigate(R.id.SignupUsername)
                return true
            }


//            R.id.action_logout -> {
//                Toast.makeText(this, "action_logout", Toast.LENGTH_LONG).show()
//                return true
//            }
//
//            android.R.id.home -> {
//                drawerLayout.openDrawer(GravityCompat.START)
//                return true
//            }
        }
        return super.onOptionsItemSelected(item)
    }
}


// App bar main activity

//    super.onCreate(savedInstanceState)
//    setContentView(R.layout.activity_main)
//
//    toolbar = findViewById(R.id.toolbar)
//    setSupportActionBar(toolbar)
//    supportActionBar?.title = "RecipeAPP"
//    supportActionBar?.setDisplayHomeAsUpEnabled(true)
//
//    drawerLayout = findViewById(R.id.drawer_layout)
//    val toggle = ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open_drawer, R.string.close_drawer)
//    drawerLayout.addDrawerListener(toggle)
//    toggle.syncState()
//
//}
//
//override fun onCreateOptionsMenu(menu: Menu?): Boolean {
//    menuInflater.inflate(R.menu.app_bar, menu)
//    return super.onCreateOptionsMenu(menu)
//}
//
//override fun onOptionsItemSelected(item: MenuItem): Boolean {
//    when (item.itemId) {
//        R.id.action_about_us -> {
//            supportFragmentManager.beginTransaction()
//                .replace(R.id.fragment_container, about_usFragment())
//                .addToBackStack(null)
//                .commit()
//            return true
//        }
//        R.id.action_logout -> {
//            Toast.makeText(this, "action_logout", Toast.LENGTH_LONG).show()
//            return true
//        }
//        android.R.id.home -> {
//            drawerLayout.openDrawer(GravityCompat.START)
//            return true
//        }
//    }
//    return super.onOptionsItemSelected(item)
//}
//
//
//
//
//
//
//
//
//    override fun onSupportNavigateUp(): Boolean {
//        return navController.navigateUp() || super.onSupportNavigateUp()
//    }
//
//
//
//
