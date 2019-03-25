package com.iambenbradley.chefbook.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.iambenbradley.chefbook.databinding.GridItemRecipeBinding
import com.iambenbradley.chefbook.model.room.Recipe
import com.iambenbradley.chefbook.model.room.RecipeSummary
import com.iambenbradley.chefbook.view.ui.RecipeGridFragmentDirections

class RecipeGridAdapter : RecyclerView.Adapter<RecipeGridAdapter.RecipeViewHolder>() {

    private var recipes: List<RecipeSummary> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = GridItemRecipeBinding.inflate(inflater, parent, false)
        return RecipeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        holder.bind(recipes[position], createOnClickListener(recipes[position].id))
    }

    override fun getItemCount(): Int {
        return recipes.size
    }

    fun replaceData(list: List<RecipeSummary>) {
        recipes = list
        notifyDataSetChanged()
    }

    private fun createOnClickListener(recipeId: Long): View.OnClickListener {
        return View.OnClickListener {
            val direction = RecipeGridFragmentDirections.actionRecipeGridToRecipeDetail(recipeId)
            it.findNavController().navigate(direction)
        }
    }


    class RecipeViewHolder(val binding: com.iambenbradley.chefbook.databinding.GridItemRecipeBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(recipe: RecipeSummary, listener: View.OnClickListener) {
            binding.apply {
                this.recipe = recipe
                root.setOnClickListener(listener)
                executePendingBindings()
            }
        }
    }
}