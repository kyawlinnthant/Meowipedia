package com.everest.domain.model.categories

import com.everest.data.model.categories.CategoryDTO

data class CategoryVO(
    val id: String,
    val name: String,
    val image: String
)

fun CategoryDTO.toVo() = CategoryVO(
    id = this.id ?: "",
    name = this.name ?: "",
    image = this.image?.url ?: ""
)
