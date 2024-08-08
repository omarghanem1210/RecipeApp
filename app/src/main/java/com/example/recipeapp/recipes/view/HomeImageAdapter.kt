package com.example.recipeapp.recipes.view

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.recipeapp.R
import com.example.recipeapp.models.Recipe

import com.example.recipeapp.recipes.view.RecyclerDataClass


class HomeImageAdapter( val context: Context,private val onItemClick: (Recipe) -> Unit) : RecyclerView.Adapter<HomeImageAdapter.ItemViewHolder>() {
    private var recipes : List<Recipe> = listOf<Recipe>()
    inner class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        val name : TextView
            get() = itemView.findViewById(R.id.HomeRecipeTitle)
        val image : ImageView
            get() = itemView.findViewById(R.id.imageView)

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_image, parent, false)
        return ItemViewHolder(itemView)

    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val currentItem = recipes[position]
        holder.name.setText(recipes.get(position)?.strMeal)
        Glide.with(context).load(currentItem.strMealThumb).into(holder.image)
        holder.itemView.setOnClickListener { onItemClick(currentItem) }
        Log.i("Result","OnBindViewHolder : ")

    }

    override fun getItemCount() : Int{
        Log.i("RESULT","Size of List is : ${recipes.size?: -1}"  )
        return recipes.size?:0 }
    fun setRecipeList( products : List<Recipe>){
        this.recipes = products
        notifyDataSetChanged()
    }
//    inner class ImageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
//
//    }
}