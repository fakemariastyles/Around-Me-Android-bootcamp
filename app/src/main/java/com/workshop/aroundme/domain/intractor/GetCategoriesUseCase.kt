package com.workshop.aroundme.domain.intractor

import com.workshop.aroundme.data.mapper.toCategory
import com.workshop.aroundme.data.repository.CategoryRepository
import com.workshop.aroundme.domain.executer.PostExecuterThread
import com.workshop.aroundme.domain.executer.UseCaseExecuterThread
import com.workshop.aroundme.domain.intractor.base.SingleUseCase
import com.workshop.aroundme.domain.model.ParentCategory
import io.reactivex.Single

class GetCategoriesUseCase(
    private val categoryRepository: CategoryRepository,
    private val useCaseExecuterThread: UseCaseExecuterThread,
    private val postExecuterThread: PostExecuterThread
) : SingleUseCase<GetCategoriesUseCase.None, List<ParentCategory>>
    (postExecuterThread, useCaseExecuterThread) {
    override fun buildSingle(params: None): Single<List<ParentCategory>> {
        return categoryRepository.getCategories().map {
            it.map {
                ParentCategory(
                    name = it.name
                    , children = it.children?.map {
                        it.toCategory()
                    }
                )
            }
        }
    }

    class None
}