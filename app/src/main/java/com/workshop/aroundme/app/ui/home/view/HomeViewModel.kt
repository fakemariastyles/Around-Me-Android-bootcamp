package com.workshop.aroundme.app.ui.home.view

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.workshop.aroundme.data.model.ParentCategoryEntity
import com.workshop.aroundme.data.model.PlaceEntity
import com.workshop.aroundme.data.repository.CategoryRepository
import com.workshop.aroundme.data.repository.PlaceRepository
import javax.inject.Inject

class HomeViewModel @Inject constructor(
    val categoryRepository: CategoryRepository
    , val placeRepository: PlaceRepository
) : ViewModel() {
    private var _places = MutableLiveData<List<PlaceEntity>>()
    var places: LiveData<List<PlaceEntity>> = _places

    private var _categories = MutableLiveData<List<ParentCategoryEntity>>()
    var categories: LiveData<List<ParentCategoryEntity>> = _categories

    fun onActivityCreated() {
        TODO()
    }

}