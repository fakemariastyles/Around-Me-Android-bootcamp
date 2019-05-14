package com.workshop.aroundme.data.repository

import com.workshop.aroundme.data.model.ParentCategoryEntity
import com.workshop.aroundme.remote.datasource.CategoryRemoteDataSource
import javax.inject.Inject
import kotlin.concurrent.thread

class CategoryRepository @Inject constructor(private val categoryRemoteDataSource: CategoryRemoteDataSource) {

    fun getCategories(success: (List<ParentCategoryEntity>?) -> Unit) {
        thread {
            val categories = categoryRemoteDataSource.getCategories()
            success(categories)
        }
    }
}