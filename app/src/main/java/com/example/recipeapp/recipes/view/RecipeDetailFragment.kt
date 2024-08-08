package com.example.recipeapp.recipes.view

import android.content.Context
import android.health.connect.datatypes.units.Length
import android.media.RouteListingPreference.Item
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.view.menu.MenuView.ItemView
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.recipeapp.R
import com.example.recipeapp.databinding.ActivityMainBinding
import com.example.recipeapp.databinding.FragmentRecipeDetailBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import java.lang.Exception


class RecipeDetailFragment : Fragment() {

    private val args: RecipeDetailFragmentArgs by navArgs()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_recipe_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val imageView: ImageView = view.findViewById(R.id.DetailImage)

        try {
            imageView.setImageResource(args.imageArg)
            Log.i("Result","OnViewCreated : ")
        }
        catch (ex:Exception){

            Toast.makeText(requireContext()," Click on image first ",Toast.LENGTH_SHORT).show()
            findNavController().navigateUp()
        }



    }

}