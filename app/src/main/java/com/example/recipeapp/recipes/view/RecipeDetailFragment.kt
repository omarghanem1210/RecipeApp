package com.example.recipeapp.recipes.view

import android.content.Context
import android.health.connect.datatypes.units.Length
import android.media.RouteListingPreference.Item
import android.os.Bundle
import android.provider.MediaStore.Video
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Button
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import android.widget.VideoView
import androidx.appcompat.view.menu.MenuView.ItemView
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.recipeapp.R
import com.example.recipeapp.database.recipe.LocalRecipe
import com.example.recipeapp.databinding.ActivityMainBinding
import com.example.recipeapp.databinding.FragmentRecipeDetailBinding
import com.example.recipeapp.models.Recipe
import com.example.recipeapp.models.UserManager
import com.example.recipeapp.network.APIClient
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView
import kotlinx.coroutines.launch
import java.lang.Exception


class RecipeDetailFragment : Fragment() {


    private val args: RecipeDetailFragmentArgs by navArgs()
    private lateinit var videoView: WebView
    private  lateinit var favoriteButton: Button



    // Using navArgs to retrieve the passed data


        override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
        ): View? {
            val recipe = args.recipeArg
            val view = inflater.inflate(R.layout.fragment_recipe_detail, container, false)
            val imageView: ImageView = view.findViewById(R.id.DetailImage)
            val detailView : TextView = view.findViewById(R.id.DetailsId)
            val titleView : TextView = view.findViewById(R.id.DetailTitle)
            val videoShow: Button = view.findViewById(R.id.videoButton)
            favoriteButton = view.findViewById(R.id.favoriteButton)
            var localDataSource = LocalRecipe.getInstance(requireContext())


            favoriteButton.setOnClickListener{
                lifecycleScope.launch {
                    recipe.userName = UserManager.currentUser?.username
                    try {
                        var favorites = localDataSource.getFavorites(recipe?.userName!!)
                        for(favorite in favorites){
                            if(recipe.strMeal == favorite.strMeal){
                                Toast.makeText(requireContext(), "This is already a favorite", Toast.LENGTH_LONG).show()
                                return@launch
                            }
                        }
                        localDataSource.insertRecipe(recipe)

                    }
                    catch(ex:Exception){
                        Toast.makeText(requireContext(), "This is already a favorite", Toast.LENGTH_LONG).show()
                    }
                    Toast.makeText(requireContext(), "Congratulations! A new favorite", Toast.LENGTH_LONG).show()
                }
            }


            videoShow.setOnClickListener {
                if(recipe.strYoutube != null){
                    val action = RecipeDetailFragmentDirections.recipeDetailFragmentToVideoFragment(recipe.strYoutube)
                    view.findNavController().navigate(action)


                }

            }

            try {
           Glide.with(this).load(recipe.strMealThumb).into(imageView)
           detailView.text = recipe.strInstructions
           titleView.text = recipe.strMeal
        }
       catch (ex:Exception){
            Toast.makeText(requireContext()," Touch A Recipe To See Its Details.. ",Toast.LENGTH_SHORT).show()
            findNavController().navigateUp()
       }

            // Load the image using Glide and the received data

            return view
        }

    private fun setupWebView() {
        videoView.settings.javaScriptEnabled = true
        videoView.settings.domStorageEnabled = true
        videoView.webViewClient = WebViewClient()  // Ensures that links open in the WebView
    }

    private fun playYouTubeVideo(url: String) {
        videoView.loadUrl(url)
    }


}







