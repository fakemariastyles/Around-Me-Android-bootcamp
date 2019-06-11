package com.workshop.aroundme.domain.model

data class ParentCategory(
    val name: String?,
    val children: List<Category>?
)

data class Category(
    val icon: String?,
    val id: Int?,
    val name: String?
)