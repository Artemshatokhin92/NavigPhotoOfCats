package com.shatokhin.dbroomphotoofcats.data.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class Vote(
    val id: Int,
    @SerialName("image_id")
    val idCat: String,
    val value: Int
)