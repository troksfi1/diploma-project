package cz.cvut.fit.nidip.troksfil.presentation.screens.parking

import androidx.compose.ui.graphics.Color
import com.google.android.gms.maps.model.LatLng

data class ParkingScreenState(
    var parkingZones: List<ParkingZone> = emptyList(),
    val nameOfLastSelectedZone: String = "",
    val bottomSheetVisible: Boolean = false,
    val lastSelectedZone: ParkingZone? = ParkingZone(
        "", "", "", Color.Cyan, listOf(listOf(LatLng(1.0, 1.0)))
    ),
    val zoneSelected: Boolean = false,
)
