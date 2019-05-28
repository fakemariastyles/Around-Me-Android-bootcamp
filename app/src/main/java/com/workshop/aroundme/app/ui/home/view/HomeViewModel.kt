package com.workshop.aroundme.app.ui.home.view

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.workshop.aroundme.data.model.ParentCategoryEntity
import com.workshop.aroundme.data.model.PlaceEntity
import com.workshop.aroundme.data.repository.CategoryRepository
import com.workshop.aroundme.data.repository.PlaceRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class HomeViewModel @Inject constructor(
    private val categoryRepository: CategoryRepository
    , private val placeRepository: PlaceRepository
) : ViewModel() {
    private var _places = MutableLiveData<List<PlaceEntity>>()
    var places: LiveData<List<PlaceEntity>> = _places

    private var _categories = MutableLiveData<List<ParentCategoryEntity>>()
    var categories: LiveData<List<ParentCategoryEntity>> = _categories

    fun onActivityCreated() {
        placeRepository.getFeaturedPlaces()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSuccess {
                _places.postValue(it)
            }
            .observeOn(Schedulers.io())
            .flatMap {
                categoryRepository.getCategories()
            }.observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                _categories.postValue(it)
            }, {})
    }

    fun onItemStarred(placeEntity: PlaceEntity){
        placeRepository.starPlace(placeEntity)
    }

}