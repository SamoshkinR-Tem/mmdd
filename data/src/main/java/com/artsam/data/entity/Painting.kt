package com.artsam.data.entity

import com.google.gson.annotations.SerializedName

data class Painting(
    @SerializedName("id") val id: String,
    @SerializedName("createdAt") val createdAt: String,
    @SerializedName("surface") val surface: String,
    @SerializedName("paint") val paint: String,
    @SerializedName("image") val image: String,
)
