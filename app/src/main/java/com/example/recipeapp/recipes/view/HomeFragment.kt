package com.example.recipeapp.recipes.view


import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
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
import com.example.recipeapp.recipes.viewModel.RecipeViewModel
import com.example.recipeapp.recipes.viewModel.RecipeViewModelFactory
import com.example.recipeapp.users.view.UserActivity


class HomeFragment : Fragment() {
    lateinit var recyclerView: RecyclerView
    lateinit var myAdapter: HomeImageAdapter
    lateinit var searchView: SearchView
    lateinit var helloUser: TextView
    lateinit var logOut: Button
    private lateinit var sharedPreferences: SharedPreferences


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
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

        sharedPreferences = context?.getSharedPreferences("user_prefs", Context.MODE_PRIVATE)!!


        helloUser = view.findViewById(R.id.textView)

        helloUser.text = "Hello " + UserManager.currentUser?.username!!

        logOut = view.findViewById(R.id.outButton)

        logOut.setOnClickListener{
            deleteUser()
            val intent = Intent(getActivity(), UserActivity::class.java)
            intent.flags = (Intent.FLAG_ACTIVITY_NEW_TASK
                    or Intent.FLAG_ACTIVITY_CLEAR_TASK);
            activity?.startActivity(intent)


        }


        recyclerView = view.findViewById(R.id.recyclerView)
        recyclerView.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
//        val data = listOf(
//            RecyclerDataClass(R.drawable.chorizo_mozarella),
//            RecyclerDataClass(R.drawable.delicious_fried_noodle_with_smoky_effect))


        myAdapter = HomeImageAdapter(requireActivity()) { recipe ->

            val action = HomeFragmentDirections.actionHomeFragmentToRecipeDetailFragment(recipe)
            view.findNavController().navigate(action)
            // Handle item click here
        }
        recyclerView.adapter = myAdapter

        viewModel.getRecipes()
        viewModel.allRecipes?.observe(requireActivity(), Observer {
            myAdapter.setRecipeList(it)
            myAdapter.notifyDataSetChanged()
        })

        searchView = view.findViewById(R.id.searchView)
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

    fun deleteUser() {
        val editor = sharedPreferences.edit()
        editor.remove("logged")
        editor.apply()
    }

}
