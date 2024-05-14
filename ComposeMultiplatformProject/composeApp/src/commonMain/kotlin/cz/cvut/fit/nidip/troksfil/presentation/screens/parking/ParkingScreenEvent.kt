package cz.cvut.fit.nidip.troksfil.presentation.screens.parking

sealed interface ParkingScreenEvent {
    data class OnParkingSearched(val text: String) : ParkingScreenEvent

    data class OnParkingZoneClicked(val name: String) : ParkingScreenEvent

    data object OnBottomSheetCloseClicked : ParkingScreenEvent

}