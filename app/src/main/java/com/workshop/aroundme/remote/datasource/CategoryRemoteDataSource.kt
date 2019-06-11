package com.workshop.aroundme.remote.datasource

import com.workshop.aroundme.data.mapper.toCategoryEntity
import com.workshop.aroundme.data.model.ParentCategoryEntity
import com.workshop.aroundme.remote.NetworkApi
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class CategoryRemoteDataSource @Inject constructor(
    private val networkApi: NetworkApi
) {

//    fun getCategories(): List<ParentCategoryEntity>? {
//        return categoryService.getCategoriesResponse()
//            .response
//            ?.map {
//                ParentCategoryEntity(
//                    name = it.key,
//                    children = it.value
//                        .map { categoryDto ->
//                            categoryDto.toCategoryEntity()
//                        }
//                )
//            }
//    }


    fun getCategories(): Single<List<ParentCategoryEntity>?> {
        return networkApi.getCategories().map {
            it.response
                ?.map {
                    ParentCategoryEntity(
                        name = it.key,
                        children = it.value
                            .map { categoryDto ->
                                categoryDto.toCategoryEntity()
                            }
                    )
                }
        }

    }
}