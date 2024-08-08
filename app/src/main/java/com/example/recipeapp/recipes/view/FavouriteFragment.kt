package com.example.recipeapp.recipes.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.recipeapp.R


class FavouriteFragment : Fragment() {
    lateinit var recyclerView : RecyclerView
    lateinit var myFavAdapter : FavouriteImageAdapter
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

        recyclerView = view.findViewById(R.id.rv_movies)
        recyclerView.layoutManager = LinearLayoutManager(requireContext() , LinearLayoutManager.VERTICAL , false)
        val data = listOf(
            RecyclerDataClass(R.drawable.chorizo_mozarella),
            RecyclerDataClass(R.drawable.delicious_fried_noodle_with_smoky_effect))

        myFavAdapter = FavouriteImageAdapter(data)
        recyclerView.adapter = myFavAdapter

    }

}