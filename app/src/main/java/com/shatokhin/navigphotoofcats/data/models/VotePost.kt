package com.shatokhin.navigphotoofcats.data.models

import kotlinx.serialization.Serializable

@Serializable
class VotePost(
    val image_id: String,
    val sub_id: String,
    val value: Int
)