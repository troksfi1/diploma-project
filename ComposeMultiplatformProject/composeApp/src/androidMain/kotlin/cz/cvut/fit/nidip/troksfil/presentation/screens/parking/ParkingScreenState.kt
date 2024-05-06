package cz.cvut.fit.nidip.troksfil.presentation.screens.parking

import androidx.compose.ui.graphics.Color
import com.google.android.gms.maps.model.LatLng
import cz.cvut.fit.nidip.troksfil.domain.model.Event
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

data class ParkingScreenState(
    var parkingZones: List<ParkingZone> = emptyList(),
    val events: StateFlow<List<Event>> = MutableStateFlow(emptyList()),
    val nameOfLastSelectedZone: ParkingZone = ParkingZone(
        "", "", "", Color.Cyan, listOf(listOf(LatLng(1.0, 1.0)))
    ),
    val bottomSheetVisible: Boolean = false
)
