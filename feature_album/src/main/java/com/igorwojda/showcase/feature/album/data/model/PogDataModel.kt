package com.igorwojda.showcase.feature.album.data.model

import com.igorwojda.showcase.feature.album.domain.model.PogDomainModel

internal data class PogDataModel(
    val index: Int,
    val series: PogSeriesDataModel
)

internal fun PogDataModel.toDomainModel() = PogDomainModel(
    index = this.index,
    series = this.series.toDomainModel()
)

