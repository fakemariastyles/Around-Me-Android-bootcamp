package com.workshop.aroundme.domain.intractor

import com.workshop.aroundme.data.mapper.toPlaceDetail
import com.workshop.aroundme.data.repository.PlaceRepository
import com.workshop.aroundme.domain.executer.PostExecuterThread
import com.workshop.aroundme.domain.executer.UseCaseExecuterThread
import com.workshop.aroundme.domain.intractor.base.SingleUseCase
import com.workshop.aroundme.domain.model.Place
import com.workshop.aroundme.domain.model.PlaceDetail
import io.reactivex.Single
import javax.inject.Inject

class GetPlaceDetailUseCase @Inject constructor(
    private val placeRepository: PlaceRepository
    , private val useCaseExecuterThread: UseCaseExecuterThread
    , private val postExecuterThread: PostExecuterThread
    , private val slug: String
) : SingleUseCase<GetPlaceDetailUseCase.None, PlaceDetail>
    (postExecuterThread, useCaseExecuterThread) {
    override fun buildSingle(params: None): Single<PlaceDetail> {
        return placeRepository.getPlaceDetail(slug).map {
            it.toPlaceDetail()
        }
    }

    class None
}