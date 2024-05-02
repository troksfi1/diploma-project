package cz.cvut.fit.nidip.troksfil.presentation.screens.home

import cz.cvut.fit.nidip.troksfil.domain.ImageButtonType
import cz.cvut.fit.nidip.troksfil.domain.model.Event
import cz.cvut.fit.nidip.troksfil.domain.model.News
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

data class HomeState(
    val news: StateFlow<List<News>> = MutableStateFlow(emptyList()),
    val events: StateFlow<List<Event>> = MutableStateFlow(emptyList()),
    val isFetchingNews: Boolean = true,
    val isFetchingEvents: Boolean = true,
    val usedImageButtons: List<ImageButtonType> = listOf(
        ImageButtonType.DEFECTS,
        ImageButtonType.MUNICIPAL_AUTHORITY,
        ImageButtonType.SERVICES,
        ImageButtonType.YOUTUBE
    )
)