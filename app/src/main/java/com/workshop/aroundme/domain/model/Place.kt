package com.workshop.aroundme.domain.model

data class Place(
    val placeId: Int,
    val name: String?,
    val address: String?,
    val likes: Int?,
    val imageUrl: String?,
    var isFavorite: Boolean,
    val slug: String?
)