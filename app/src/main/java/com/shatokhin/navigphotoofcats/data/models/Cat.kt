package com.shatokhin.navigphotoofcats.data.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Cat(
    val id: String,
    @SerialName("url")
    val urlImage: String,
)