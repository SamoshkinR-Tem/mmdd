package com.artsam.data.repo

import com.artsam.data.api.PaintingsApi
import com.artsam.data.entity.Painting
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class PaintingsRepository(
    private val api: Lazy<PaintingsApi>
) {

    fun fetch(
        branch: String = "master",
        path: String = "paintings-list.json",
    ): Flow<List<Painting>> = flow {
        emit(
            api.value.fetch(
                branch,
                path,
            ).paintings
        )
    }
}
