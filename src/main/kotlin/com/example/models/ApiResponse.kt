package com.example.models

import kotlinx.serialization.Serializable
import org.koin.core.logger.MESSAGE

@Serializable
data class ApiResponse(
    val success:Boolean,
    val message: String? = null,
    val prevPage:Int? = null,
    val nextPage:Int?=null,
    val heroes:List<Hero> = emptyList()
)
