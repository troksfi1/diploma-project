package cz.cvut.fit.nidip.troksfil.presentation.screens.parking

sealed interface ParkingScreenEvent {

    object OnParkingZoneClicked : ParkingScreenEvent
    object OnParkingSearched : ParkingScreenEvent

}