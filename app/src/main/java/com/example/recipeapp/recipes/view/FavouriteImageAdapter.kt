package com.example.recipeapp.recipes.view

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.recipeapp.R
import com.example.recipeapp.database.recipe.LocalRecipe
import com.example.recipeapp.models.Recipe
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class FavouriteImageAdapter( val context: Context,private val onItemClick: (Recipe) -> Unit) : RecyclerView.Adapter<FavouriteImageAdapter.ItemViewHolder>() {
    private var recipes : MutableList<Recipe> = arrayListOf()
    inner class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        val name : TextView
            get() = itemView.findViewById(R.id.FavText)
        val image : ImageView
            get() = itemView.findViewById(R.id.favImage)

        val removeButton: Button = itemView.findViewById(R.id.favButton)

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_fav_image, parent, false)
        return ItemViewHolder(itemView)

    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val currentItem = recipes[position]
        holder.name.setText(recipes.get(position)?.strMeal)
        Glide.with(context).load(currentItem.strMealThumb).into(holder.image)
        holder.itemView.setOnClickListener { onItemClick(currentItem) }
        var localDataSource = LocalRecipe.getInstance(context)


        holder.removeButton.setOnClickListener{
            Toast.makeText(context, "Item removed", Toast.LENGTH_LONG).show()
            removeAt(position)
            GlobalScope.launch {
                localDataSource.deleteRecipe(currentItem)
            }


        }
        Log.i("Result","OnBindViewHolder : ")

    }

    override fun getItemCount() : Int{
        Log.i("RESULT","Size of List is : ${recipes.size?: -1}"  )
        return recipes.size?:0 }
    fun setRecipeList( products : MutableList<Recipe>){
        this.recipes = products
        notifyDataSetChanged()
    }

    private fun removeAt(position: Int) {
        recipes.removeAt(position)
        notifyItemRemoved(position)
        notifyItemRangeChanged(position, recipes.size)
    }

//    inner class ImageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
//
//    }
}