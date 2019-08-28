package com.igorwojda.showcase.feature.album.domain.repository

import com.igorwojda.showcase.feature.album.domain.model.AlbumDomainModel
import com.igorwojda.showcase.feature.album.domain.model.PogSeriesDomainModel

internal interface AlbumRepository {

    suspend fun getAlbumInfo(artistName: String, albumName: String, mbId: String?): AlbumDomainModel?

    suspend fun searchAlbum(phrase: String): List<AlbumDomainModel>

    suspend fun getPogSeriesList() : List<PogSeriesDomainModel>
}
