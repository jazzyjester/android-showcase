package com.igorwojda.showcase.feature.album.data.repository

import android.content.Context
import com.igorwojda.showcase.feature.album.data.model.PogSeriesResponseDataModel
import com.igorwojda.showcase.feature.album.data.model.toDomainModel
import com.igorwojda.showcase.feature.album.data.retrofit.service.AlbumRetrofitService
import com.igorwojda.showcase.feature.album.domain.model.PogSeriesDomainModel
import com.igorwojda.showcase.feature.album.domain.repository.AlbumRepository
import com.igorwojda.showcase.library.base.presentation.extension.loadJSONFromAsset
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import timber.log.Timber

internal class AlbumRepositoryImpl(
    private val albumRetrofitService: AlbumRetrofitService, private val context: Context
) : AlbumRepository {
    override suspend fun getPogSeriesList(): List<PogSeriesDomainModel> {
        val data = context.loadJSONFromAsset("data.json")
        val moshi: Moshi = Moshi.Builder().build()
        val adapter: JsonAdapter<PogSeriesResponseDataModel> = moshi.adapter(PogSeriesResponseDataModel::class.java)
        val seriesList = adapter.fromJson(data)

        Timber.d("Ronny $seriesList")
        return seriesList?.toDomainModel()!!.series
    }

    override suspend fun getAlbumInfo(artistName: String, albumName: String, mbId: String?) =
        albumRetrofitService.getAlbumInfoAsync(artistName, albumName, mbId)
            ?.album
            ?.let { it }
            ?.toDomainModel()

    override suspend fun searchAlbum(phrase: String) =
        albumRetrofitService.searchAlbumAsync(phrase)
            .results
            .albumMatches
            .album
            .map { it.toDomainModel() }
}
