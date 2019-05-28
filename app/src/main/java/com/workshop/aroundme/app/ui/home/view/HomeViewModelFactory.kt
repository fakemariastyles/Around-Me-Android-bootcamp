package com.workshop.aroundme.app.ui.home.view

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.workshop.aroundme.data.repository.CategoryRepository
import com.workshop.aroundme.data.repository.PlaceRepository

class HomeViewModelFactory(
    private val categoryRepository: CategoryRepository
    , private val placeRepository: PlaceRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return HomeViewModel(categoryRepository,placeRepository) as T
    }

}