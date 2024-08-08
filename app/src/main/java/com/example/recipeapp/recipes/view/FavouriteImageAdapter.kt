package com.example.recipeapp.recipes.view

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.recipeapp.R


    class FavouriteImageAdapter(val data: List<RecyclerDataClass>) : RecyclerView.Adapter<FavouriteImageAdapter.ItemViewHolder>() {

        class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            val image: ImageView = itemView.findViewById(R.id.favImage)

        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
            val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_fav_image, parent, false)
            return ItemViewHolder(itemView)

        }

        override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
            val currentItem = data[position]
            holder.image.setImageResource(currentItem.dataImage)

        }

        override fun getItemCount() = data.size
    }
