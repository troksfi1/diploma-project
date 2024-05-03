package cz.cvut.fit.nidip.troksfil.presentation.screens.home

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import co.touchlab.kermit.Logger
import cz.cvut.fit.nidip.troksfil.data.repository.RepositoryImpl
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeScreenModel(
    private val repository: RepositoryImpl
) : ScreenModel {
    private var counter1 = 0
    private var counter2 = 0

    private val _state = MutableStateFlow(HomeState())
    val state = _state.asStateFlow()

    private val coroutineExceptionHandler = CoroutineExceptionHandler { _, throwable ->
        throwable.printStackTrace()
    }

    private fun onEvent(event: HomeEvent) {
        when (event) {
            HomeEvent.OnInit -> {

            }
        }
    }

    init {
        screenModelScope.launch(Dispatchers.IO + coroutineExceptionHandler) {
            val news = repository.getAllNews()
            news.collect {
                counter1 += 1           //todo replace
                Logger.d("TAG $counter1")
                if (counter1 == 2) {
                    _state.update { state ->
                        state.copy(
                            isFetchingNews = false
                        )

                    }
                }
                _state.update { state ->
                    state.copy(
                        news = news,
                    )
                }
                Logger.d("TAG OnInit News list changed")
            }
        }
        screenModelScope.launch(Dispatchers.IO + coroutineExceptionHandler) {
            val events = repository.getNewestEvents()

            events.collect {
                counter2 += 1
                Logger.d("TAG $counter2")
                if (counter2 == 2) {
                    _state.update { state ->
                        state.copy(
                            isFetchingEvents = false
                        )
                    }
                }
                _state.update { state ->
                    state.copy(
                        events = events,
                    )
                }
                Logger.d("TAG OnInit Events list changed")
            }
        }
    }
}