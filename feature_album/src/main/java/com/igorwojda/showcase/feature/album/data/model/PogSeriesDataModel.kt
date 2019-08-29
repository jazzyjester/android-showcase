package com.igorwojda.showcase.feature.album.data.model

import com.igorwojda.showcase.feature.album.domain.model.PogSeriesDomainModel
import com.squareup.moshi.Json

internal data class PogSeriesDataModel(
    val name: String,
    val index: Int,
    @field:Json(name = "background") val backgroundResourceName: String?
)

internal fun PogSeriesDataModel.toDomainModel() = PogSeriesDomainModel(
    name = this.name,
    index = this.index,
    backgroundResourceName = this.backgroundResourceName

)

