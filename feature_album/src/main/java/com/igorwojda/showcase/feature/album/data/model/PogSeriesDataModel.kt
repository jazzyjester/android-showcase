package com.igorwojda.showcase.feature.album.data.model

import com.igorwojda.showcase.feature.album.domain.model.PogSeriesDomainModel

internal data class PogSeriesDataModel(
    val name: String,
    val index: Int
)

internal fun PogSeriesDataModel.toDomainModel() = PogSeriesDomainModel(
    name = this.name,
    index = this.index
)

