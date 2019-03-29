package com.iambenbradley.chefbook.view.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import com.google.android.material.textfield.TextInputEditText
import com.iambenbradley.chefbook.databinding.FragmentRecipeGridBinding
import com.iambenbradley.chefbook.model.room.RecipeSummary
import com.iambenbradley.chefbook.util.BackdropAnimator
import com.iambenbradley.chefbook.util.BackdropAnimatorListener
import com.iambenbradley.chefbook.util.TextValidator
import com.iambenbradley.chefbook.view.adapter.RecipeGridAdapter
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
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        val adapter = RecipeGridAdapter()
        binding.recipeGridRecyclerview.apply {
            layoutManager = GridLayoutManager(context, 2)
            this.adapter = adapter
        }
        viewModel.recipesResult().observe(this, Observer<List<RecipeSummary>> {
            adapter.replaceData(it)
            if (adapter.itemCount == 0) {
                binding.emptyView.visibility = View.VISIBLE
            } else {
                binding.emptyView.visibility = View.GONE
            }
        })
        val listener = BackdropAnimatorListener(binding.gridFrontSheet, binding.gridBackdrop, binding.frontSheetScrim)
        binding.detailSearchButton.setOnClickListener(listener)
        binding.searchCancelButton.setOnClickListener(listener)
        binding.frontSheetScrim.setOnClickListener(listener)
        binding.searchFindButton.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                val animator = BackdropAnimator(binding.gridFrontSheet, binding.gridBackdrop, binding.frontSheetScrim)
                animator.animate()
                listener.backdropShown = !listener.backdropShown
                viewModel.findRecipes()
            }
        })
        binding.searchEditText.addTextChangedListener(object : TextValidator(binding.searchEditText) {
            override fun validate(v: TextInputEditText, s: String) {
                viewModel.searchButtonEnabled.value = s.isNotBlank()
            }
        })
        return binding.root
    }
}