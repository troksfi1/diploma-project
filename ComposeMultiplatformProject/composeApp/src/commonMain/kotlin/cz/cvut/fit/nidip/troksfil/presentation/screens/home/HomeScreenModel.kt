package cz.cvut.fit.nidip.troksfil.presentation.screens.home

import cafe.adriel.voyager.core.model.ScreenModel
import co.touchlab.kermit.Logger
import cz.cvut.fit.nidip.troksfil.data.repository.RepositoryImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeScreenModel(
    private val repository: RepositoryImpl
) : ScreenModel {

    private val _state = MutableStateFlow(HomeState())
    val state = _state.asStateFlow()    //ala _uiState?
    //private val uriHandler = LocalUriHandler.current

    fun onEvent(event: HomeEvent) {
        when (event) {

            /*HomeEvent.OnButtonYouTubeClicked -> {
                uriHandler.openUri("https://www.youtube.com/@mestopribram1671")
                _state.update { state ->
                    state.copy(

                    )
                }
            }*/

            HomeEvent.OnInit -> {
                GlobalScope.launch(Dispatchers.IO) {
                    val news = repository.getAllNews()
                    val events = repository.getAllEvents()
                    news.collect {
                        _state.update { state ->
                            state.copy(
                                news = news,
                                events = events
                            )
                        }
                        Logger.d("OnInit News list changed")
                    }
                }
            }
        }
    }

    init {
        onEvent(HomeEvent.OnInit)
    }
}