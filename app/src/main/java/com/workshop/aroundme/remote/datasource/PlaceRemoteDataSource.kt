package com.workshop.aroundme.remote.datasource

import com.workshop.aroundme.remote.NetworkApi
import com.workshop.aroundme.remote.model.response.DetailResponseDto
import com.workshop.aroundme.remote.model.response.PlaceDto
import io.reactivex.Single
import javax.inject.Inject

class PlaceRemoteDataSource @Inject constructor(
    private val networkApi: NetworkApi
) {

//    fun getFeaturedPlaces(): List<PlaceDto>? {
//        return placeService.getFeaturedPlacesResponse()
//            .response?.items
//    }
//
//    fun getPlaceDetail(slug: String): DetailResponseDto? {
//        return placeService.getPlaceDetailResponse(slug)
//            .response
//    }

    fun getFeaturedPlaces(): Single<List<PlaceDto>?> {
        return networkApi.getFeaturedPlaces().map {
            it.response?.items
        }
    }

    fun getPlaceDetail(slug:String):Single<DetailResponseDto?>{
        return networkApi.getPlaceDetail(slug).map {
            it.response
        }
    }
}