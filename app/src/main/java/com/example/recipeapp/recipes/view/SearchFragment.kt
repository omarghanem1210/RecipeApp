package com.example.recipeapp.recipes.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.recipeapp.R
import com.example.recipeapp.database.recipe.LocalRecipe
import com.example.recipeapp.network.APIClient
import com.example.recipeapp.recipes.repo.RecipesRepositoryImplementation
import com.example.recipeapp.recipes.viewModel.RecipeViewModel
import com.example.recipeapp.recipes.viewModel.RecipeViewModelFactory

class SearchFragment : Fragment() {
    private lateinit var searchView: SearchView
    lateinit var recyclerView: RecyclerView
    lateinit var myAdapter: HomeImageAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var localDataSource = LocalRecipe.getInstance(requireActivity())
        var remoteDataSource = APIClient
        var recipeViewModelFactory = RecipeViewModelFactory(
            RecipesRepositoryImplementation(
                LocalRecipe.getInstance(requireActivity()), remoteDataSource
            )
        )
        val repository = RecipesRepositoryImplementation(localDataSource, remoteDataSource)
        var viewModel: RecipeViewModel =
            ViewModelProvider(this, recipeViewModelFactory).get(RecipeViewModel::class.java)


        searchView = view.findViewById(R.id.searchView)
        recyclerView = view.findViewById(R.id.recyclerView)
        recyclerView.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)

        myAdapter = HomeImageAdapter(requireActivity()) { recipe ->

            val action = SearchFragmentDirections.actionSearchFragmentToRecipeDetailFragment(recipe)
            view.findNavController().navigate(action)
            // Handle item click here
        }

        recyclerView.adapter = myAdapter

        viewModel.getRecipes()
        viewModel.allRecipes?.observe(requireActivity(), Observer {
            myAdapter.setRecipeList(it)
            myAdapter.notifyDataSetChanged()
        })


        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false

            }

            override fun onQueryTextChange(newText: String?): Boolean {
                viewModel.getSearchRecipes(newText.orEmpty())

                return true
            }


        })





    }

}
