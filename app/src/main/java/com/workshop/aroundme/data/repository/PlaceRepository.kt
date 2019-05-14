package com.workshop.aroundme.data.repository

import androidx.annotation.WorkerThread
import com.workshop.aroundme.data.mapper.toLocalPlace
import com.workshop.aroundme.data.mapper.toPlaceDetailEntity
import com.workshop.aroundme.data.mapper.toPlaceEntity
import com.workshop.aroundme.data.model.PlaceDetailEntity
import com.workshop.aroundme.data.model.PlaceEntity
import com.workshop.aroundme.local.datasource.PlaceLocalDataSource
import com.workshop.aroundme.remote.datasource.PlaceRemoteDataSource
import javax.inject.Inject
import kotlin.concurrent.thread

class PlaceRepository @Inject constructor(
    private val placeLocalDataSource: PlaceLocalDataSource,
    private val placeRemoteDataSource: PlaceRemoteDataSource
) {

    fun getFeaturedPlaces(success: (List<PlaceEntity>?) -> Unit) {
        Thread {
            val result = placeRemoteDataSource.getFeaturedPlaces()?.map { placeDto ->
                placeDto.toPlaceEntity()
            }
            success(result)
        }.start()
    }

    fun getPlaceDetail(slug: String, success: (PlaceDetailEntity?) -> Unit) {
        thread {
            val entity = placeRemoteDataSource.getPlaceDetail(slug)?.toPlaceDetailEntity()
            success(entity)
        }
    }

    @WorkerThread
    fun getStarredPlaces(success: (List<PlaceEntity>) -> Unit) {
        thread {
            val places = placeLocalDataSource.getStarredPlaces().map { localPlace ->
                localPlace.toPlaceEntity()
            }
            success(places)
        }
    }

    @WorkerThread
    fun starPlace(placeEntity: PlaceEntity) {
        thread {
            placeLocalDataSource.starPlace(placeEntity.toLocalPlace())
        }
    }
}