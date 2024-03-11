package cz.cvut.fit.nidip.troksfil.presentation.screens.home

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import cz.cvut.fit.nidip.troksfil.data.repository.RepositoryImpl
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeScreenModel(
    private val repository: RepositoryImpl
) : ScreenModel {

    private val _state = MutableStateFlow(HomeState())
    val state = _state.asStateFlow()
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
                screenModelScope.launch {
                    _state.update { state ->
                        state.copy(
                            news = repository.getAllNews(),
                            events = repository.getAllEvents()
                        )
                    }
                }
            }

            else -> Unit
        }
    }

    init {
        HomeEvent.OnInit
    }

}