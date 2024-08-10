package com.example.recipeapp.recipes.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.recipeapp.R
import com.example.recipeapp.database.recipe.LocalRecipe
import com.example.recipeapp.models.UserManager
import com.example.recipeapp.network.APIClient
import com.example.recipeapp.recipes.repo.RecipesRepositoryImplementation
import com.example.recipeapp.recipes.viewModel.FavoritesViewModel
import com.example.recipeapp.recipes.viewModel.FavoritesViewModelFactory


class FavouriteFragment : Fragment() {
    lateinit var recyclerView: RecyclerView
    lateinit var myAdapter: FavouriteImageAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favourite, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var localDataSource = LocalRecipe.getInstance(requireActivity())
        var remoteDataSource = APIClient
        var favoritesViewModelFactory = FavoritesViewModelFactory(
            RecipesRepositoryImplementation(
                LocalRecipe.getInstance(requireActivity()), remoteDataSource
            )
        )
        val repository = RecipesRepositoryImplementation(localDataSource, remoteDataSource)
        var viewModel: FavoritesViewModel =
            ViewModelProvider(this, favoritesViewModelFactory).get(FavoritesViewModel::class.java)



        recyclerView = view.findViewById(R.id.rv_movies)
        recyclerView.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
//        val data = listOf(
//            RecyclerDataClass(R.drawable.chorizo_mozarella),
//            RecyclerDataClass(R.drawable.delicious_fried_noodle_with_smoky_effect))


        myAdapter = FavouriteImageAdapter(requireActivity()) { recipe ->

            val action = FavouriteFragmentDirections.actionFavoritesFragmentToRecipeDetailFragment(recipe)
            view.findNavController().navigate(action)
            // Handle item click here
        }
        recyclerView.adapter = myAdapter

        viewModel.getFavorites(UserManager.currentUser?.username!!)
        viewModel.favorites.observe(requireActivity(), Observer {
            myAdapter.setRecipeList(it)
            myAdapter.notifyDataSetChanged()
        })


    }
}
