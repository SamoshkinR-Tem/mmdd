package com.artsam.data.model

import com.artsam.data.entity.Painting
import com.google.gson.annotations.SerializedName

data class PaintingsResponse(
    @SerializedName("paintings")
    val paintings: List<Painting>,
)
