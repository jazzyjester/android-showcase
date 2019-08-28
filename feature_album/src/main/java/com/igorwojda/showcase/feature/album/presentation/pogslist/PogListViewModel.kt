package com.igorwojda.showcase.feature.album.presentation.pogslist

import androidx.lifecycle.viewModelScope
import com.igorwojda.showcase.base.presentation.viewmodel.BaseAction
import com.igorwojda.showcase.base.presentation.viewmodel.BaseViewModel
import com.igorwojda.showcase.base.presentation.viewmodel.BaseViewState
import com.igorwojda.showcase.feature.album.domain.model.PogDomainModel
import com.igorwojda.showcase.feature.album.domain.usecase.GetPogListUseCase
import kotlinx.coroutines.launch

internal class PogListViewModel(
    private val getPogListUseCase: GetPogListUseCase
) : BaseViewModel<PogListViewModel.ViewState, PogListViewModel.Action>(ViewState()) {

    override fun onLoadData() {
        getPogList()
    }

    override fun onReduceState(viewAction: Action) = when (viewAction) {
        is Action.PogSeriesListLoadingSuccess -> state.copy(
            isLoading = false,
            isError = false,
            pogList = viewAction.pogList
        )
        is Action.PogSeriesListLoadingFailure -> state.copy(
            isLoading = false,
            isError = true,
            pogList = listOf()
        )
    }

    private fun getPogList() {
        viewModelScope.launch {
            getPogListUseCase.execute().also {
                if (it.isNotEmpty()) {
                    sendAction(Action.PogSeriesListLoadingSuccess(it))
                } else {
                    sendAction(Action.PogSeriesListLoadingFailure)
                }
            }
        }
    }

    internal data class ViewState(
        val isLoading: Boolean = true,
        val isError: Boolean = false,
        val pogList: List<PogDomainModel> = listOf()
    ) : BaseViewState

    internal sealed class Action : BaseAction {
        class PogSeriesListLoadingSuccess(val pogList: List<PogDomainModel>) : Action()
        object PogSeriesListLoadingFailure : Action()
    }
}
