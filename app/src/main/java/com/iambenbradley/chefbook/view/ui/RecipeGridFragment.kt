package com.iambenbradley.chefbook.view.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.iambenbradley.chefbook.databinding.FragmentRecipeGridBinding
import com.iambenbradley.chefbook.viewmodel.RecipeGridViewModel
import com.iambenbradley.chefbook.viewmodel.RecipeGridViewModelFactory
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

class RecipeGridFragment : Fragment() {

    @Inject
    lateinit var factory: RecipeGridViewModelFactory
    lateinit var viewModel: RecipeGridViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        AndroidSupportInjection.inject(this)
        val viewModel = ViewModelProviders.of(this, factory).get(RecipeGridViewModel::class.java)
        val binding = FragmentRecipeGridBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        return binding.root
    }
}