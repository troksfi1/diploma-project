package presentation.screens.home

import data.repository.FakeNewsRepositoryImpl
import domain.model.News

data class HomeState(
    val news: List<News> = FakeNewsRepositoryImpl().getAllNews()//emptyList(),
)