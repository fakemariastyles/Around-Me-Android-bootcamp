package com.workshop.aroundme.domain.intractor

import com.workshop.aroundme.data.mapper.toPlace
import com.workshop.aroundme.data.repository.PlaceRepository
import com.workshop.aroundme.domain.executer.PostExecuterThread
import com.workshop.aroundme.domain.executer.UseCaseExecuterThread
import com.workshop.aroundme.domain.intractor.base.SingleUseCase
import com.workshop.aroundme.domain.model.Place
import io.reactivex.Single
import javax.inject.Inject

class GetFeaturedPlacesUseCase @Inject constructor
    (
    private val placeRepository: PlaceRepository
    , private val useCaseExecuterThread: UseCaseExecuterThread
    , private val postExecuterThread: PostExecuterThread
) :
    SingleUseCase<GetFeaturedPlacesUseCase.None, List<Place>>
        (postExecuterThread, useCaseExecuterThread) {

    override fun buildSingle(params: None): Single<List<Place>> {
        return placeRepository.getFeaturedPlaces().map {
            it.map {
                it.toPlace()
            }
        }
    }
    class None
}