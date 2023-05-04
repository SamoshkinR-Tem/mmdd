package com.artsam.data.repo

import com.artsam.data.api.PaintingsApi
import com.artsam.data.entity.Painting
import kotlinx.coroutines.flow.*

class PaintingsRepository(
    private val api: Lazy<PaintingsApi>
) {

    fun fetch(
        branch: String = "master",
        path: String = "paintings-list.json",
    ): Flow<List<Painting>> = flow {
        val paintings = api.value.fetch(branch, path).paintings
        emit(paintings)
    }
        //.map { paintings -> paintings.filter { it.id == "" } }
        // Intermediate operation to save the latest news in the cache
        //.onEach { news -> saveInCache(news) }
        // flowOn affects the upstream flow ↑
}
