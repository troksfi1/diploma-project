package cz.cvut.fit.nidip.troksfil.presentation.screens.parking

import androidx.compose.ui.graphics.Color
import com.google.android.gms.maps.model.LatLng

data class ParkingZone(
    val name: String,
    val paymentUrl: String,
    val description: String,
    val color: Color,
    var parkingSlotPolygons: List<List<LatLng>>
)