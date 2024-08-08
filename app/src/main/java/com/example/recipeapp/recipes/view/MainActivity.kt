package com.example.recipeapp

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.recipeapp.users.SearchFragment
//import com.example.recipeapp.users.VideoPlayerFragment


class MainActivity : AppCompatActivity() {

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

       if (savedInstanceState == null) {
           // Add the SearchFragment to the activity if not already added
            supportFragmentManager.beginTransaction()
              .replace(R.id.fragment_container, SearchFragment())
              .commit()
      }
        // Assuming you have a button to launch the video player
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, RecipeFragment())
                .commit()
        }
    }
}
