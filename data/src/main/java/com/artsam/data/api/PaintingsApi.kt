package com.artsam.data.api

import com.artsam.data.model.PaintingsResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface PaintingsApi {

    @GET("raw/{branch}/{path}")
    suspend fun fetch(
        @Path("branch") branch: String,
        @Path("path") path: String,
    ): PaintingsResponse
}
