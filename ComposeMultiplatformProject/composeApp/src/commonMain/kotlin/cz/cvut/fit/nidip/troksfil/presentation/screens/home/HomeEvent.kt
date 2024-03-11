package cz.cvut.fit.nidip.troksfil.presentation.screens.home

sealed interface HomeEvent {

    object OnInit : HomeEvent

    //data class OnButtonYouTubeClicked(val uriHandler: UriHandler) : HomeEvent

}