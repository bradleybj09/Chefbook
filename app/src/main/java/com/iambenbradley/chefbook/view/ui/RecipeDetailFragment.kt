package com.iambenbradley.chefbook.view.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.navArgs
import com.iambenbradley.chefbook.databinding.FragmentRecipeDetailBinding
import com.iambenbradley.chefbook.model.RecipeFull
import com.iambenbradley.chefbook.viewmodel.RecipeDetailViewModel
import com.iambenbradley.chefbook.viewmodel.RecipeDetailViewModelFactory
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

class RecipeDetailFragment : Fragment() {

    @Inject
    lateinit var factory: RecipeDetailViewModelFactory
    lateinit var viewModel: RecipeDetailViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        AndroidSupportInjection.inject(this)
        viewModel = ViewModelProviders.of(this, factory).get(RecipeDetailViewModel::class.java)
        val args: RecipeDetailFragmentArgs by navArgs()
        viewModel.setup(args.recipeId)
        val binding = FragmentRecipeDetailBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        viewModel.recipe().observe(this, Observer<RecipeFull> {
            binding.recipe = it
        })
        return binding.root
    }


}