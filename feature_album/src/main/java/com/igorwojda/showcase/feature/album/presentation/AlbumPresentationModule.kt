package com.igorwojda.showcase.feature.album.presentation

import androidx.fragment.app.Fragment
import coil.ImageLoader
import com.igorwojda.showcase.base.di.KotlinViewModelProvider
import com.igorwojda.showcase.feature.album.FEATURE_NAME
import com.igorwojda.showcase.feature.album.presentation.albumdetail.AlbumDetailViewModel
import com.igorwojda.showcase.feature.album.presentation.albumlist.AlbumListViewModel
import com.igorwojda.showcase.feature.album.presentation.albumlist.recyclerview.AlbumAdapter
import com.igorwojda.showcase.feature.album.presentation.pogslist.PogListViewModel
import com.igorwojda.showcase.feature.album.presentation.pogslist.recyclerview.PogsAdapter
import org.kodein.di.Kodein
import org.kodein.di.android.x.AndroidLifecycleScope
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.scoped
import org.kodein.di.generic.singleton

internal val presentationModule = Kodein.Module("${FEATURE_NAME}PresentationModule") {

    // AlbumList
    bind<AlbumListViewModel>() with scoped<Fragment>(AndroidLifecycleScope).singleton {
        KotlinViewModelProvider.of(context) { AlbumListViewModel(instance()) }
    }

    // Pog List
    bind<PogListViewModel>() with scoped<Fragment>(AndroidLifecycleScope).singleton {
        KotlinViewModelProvider.of(context) { PogListViewModel(instance()) }
    }


    bind() from singleton { PogsAdapter() }

    bind() from singleton { AlbumAdapter() }

    bind() from singleton { ImageLoader(instance()) }

    // AlbumDetails
    bind<AlbumDetailViewModel>() with scoped<Fragment>(AndroidLifecycleScope).singleton {
        KotlinViewModelProvider.of(context) { AlbumDetailViewModel(instance(), instance()) }
    }
}
