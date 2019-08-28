package com.igorwojda.showcase.feature.album.domain.usecase

import com.igorwojda.showcase.feature.album.domain.model.PogDomainModel
import com.igorwojda.showcase.feature.album.domain.repository.AlbumRepository

internal class GetPogListUseCase(
    private val albumRepository: AlbumRepository
) {
    suspend fun execute(): List<PogDomainModel> {
        val pogSeriesList = albumRepository.getPogSeriesList()
        val pogList = mutableListOf<PogDomainModel>()
        // Create the whole pogs list
        pogSeriesList.forEach { series ->
            (series.index..series.index + 29).forEach { pogIndex ->
                pogList.add(PogDomainModel(pogIndex, series))
            }
        }
        return pogList
    }
}

