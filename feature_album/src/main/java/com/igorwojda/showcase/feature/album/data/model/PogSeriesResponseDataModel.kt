package com.igorwojda.showcase.feature.album.data.model

import com.igorwojda.showcase.feature.album.domain.model.PogSeriesDomainModel
import com.igorwojda.showcase.feature.album.domain.model.PogSeriesResponseDomainModel

internal data class PogSeriesResponseDataModel(
    val series: List<PogSeriesDataModel>
)

internal fun PogSeriesResponseDataModel.toDomainModel(): PogSeriesResponseDomainModel {
    val s = mutableListOf<PogSeriesDomainModel>()
    series.forEach { s.add(it.toDomainModel()) }
    return PogSeriesResponseDomainModel(series = s)
}

