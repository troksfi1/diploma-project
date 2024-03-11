package cz.cvut.fit.nidip.troksfil.presentation.screens.home

import cz.cvut.fit.nidip.troksfil.domain.model.Event
import cz.cvut.fit.nidip.troksfil.domain.model.News

data class HomeState(
    val news: List<News> = emptyList(),
    val events: List<Event> = emptyList(),
)