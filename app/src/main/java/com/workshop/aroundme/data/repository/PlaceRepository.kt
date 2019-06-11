package com.workshop.aroundme.data.repository

import androidx.annotation.WorkerThread
import com.workshop.aroundme.data.mapper.toLocalPlace
import com.workshop.aroundme.data.mapper.toPlaceDetailEntity
import com.workshop.aroundme.data.mapper.toPlaceEntity
import com.workshop.aroundme.data.model.PlaceDetailEntity
import com.workshop.aroundme.data.model.PlaceEntity
import com.workshop.aroundme.local.datasource.PlaceLocalDataSource
import com.workshop.aroundme.remote.datasource.PlaceRemoteDataSource
import io.reactivex.Single
import javax.inject.Inject
import kotlin.concurrent.thread

class PlaceRepository @Inject constructor(
    private val placeLocalDataSource: PlaceLocalDataSource,
    private val placeRemoteDataSource: PlaceRemoteDataSource
) {

    fun getFeaturedPlaces(): Single<List<PlaceEntity>?> {
        return placeRemoteDataSource.getFeaturedPlaces().map { list ->
            list.map { placeDto ->
                placeDto.toPlaceEntity()
            }
        }
    }

    fun getPlaceDetail(slug: String): Single<PlaceDetailEntity?> {
        return placeRemoteDataSource.getPlaceDetail(slug)
            .map { detailResponseDto ->
                detailResponseDto.toPlaceDetailEntity()
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