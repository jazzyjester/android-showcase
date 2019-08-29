package com.igorwojda.showcase.feature.album.presentation.pogslist

import android.os.Bundle
import android.view.View
import com.igorwojda.showcase.feature.album.R
import com.igorwojda.showcase.feature.album.presentation.albumlist.recyclerview.GridAutofitLayoutManager
import com.igorwojda.showcase.feature.album.presentation.pogslist.recyclerview.PogsAdapter
import com.igorwojda.showcase.library.base.presentation.extension.observe
import com.igorwojda.showcase.library.base.presentation.fragment.BaseContainerFragment
import com.pawegio.kandroid.visible
import kotlinx.android.synthetic.main.fragment_album_list.*
import org.kodein.di.generic.instance

class PogListFragment : BaseContainerFragment() {

    private val viewModel: PogListViewModel by instance()

    override val layoutResourceId = R.layout.fragment_album_list

    private val pogAdapter: PogsAdapter by instance()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val context = checkNotNull(context)

        pogAdapter.setOnDebouncedClickListener {
            //            val navDirections = AlbumListFragmentDirections.actionAlbumListToAlbumDetail(it.artist, it.name, it.mbId)
//            findNavController().navigate(navDirections)
        }

        recyclerView.apply {
            setHasFixedSize(true)
            val columnWidth = context.resources.getDimension(R.dimen.image_size).toInt()
            layoutManager =
                GridAutofitLayoutManager(
                    context,
                    columnWidth
                )

            adapter = pogAdapter
        }

        observe(viewModel.stateLiveData, ::onStateChange)
        viewModel.loadData()
    }

    private fun onStateChange(state: PogListViewModel.ViewState) {
        pogAdapter.pogList = state.pogList
        progressBar.visible = state.isLoading
        errorAnimation.visible = state.isError
    }
}
