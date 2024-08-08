package com.example.recipeapp.recipes.view

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI.setupWithNavController
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.recipeapp.R
import com.example.recipeapp.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    lateinit var navController:NavController
    lateinit var binding:ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        binding =ActivityMainBinding.inflate((layoutInflater))
//        setContentView(binding.root)
          setContentView(R.layout.activity_main)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)


        val navHostFragment= supportFragmentManager.findFragmentById(R.id.nav_host) as NavHostFragment
        navController = navHostFragment.navController
        val bottomNavigationView= findViewById<BottomNavigationView>(R.id.bottom_nav)
        setupWithNavController(bottomNavigationView,navController)
        Log.i("Result","MainNavigate : ")





//        binding.bottomNav.setOnItemSelectedListener {
//
//            when (it.itemId) {
//
//                R.id.home -> NavController.navigate(R.id.homeFragment)
//                R.id.recipe_detail -> NavController.navigate(R.id.recipeDetailFragment)
//                R.id.fav -> NavController.navigate(R.id.favouiteFragment)
//
//            else -> {
//
//            }
//
//
//        }
//            true
//        }








    }
    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }


//    fun replaceFragment(fragment: Fragment){
//        val fragmentManager = supportFragmentManager
//        val fragmentTransaction = fragmentManager.beginTransaction()
////        fragmentTransaction.replace(R.id.constraintLayout,fragment)
//        fragmentTransaction.commit()

    }

