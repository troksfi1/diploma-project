package cz.cvut.fit.nidip.troksfil.presentation.screens.parking

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.material3.rememberStandardBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.Dash
import com.google.android.gms.maps.model.Gap
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MapStyleOptions
import com.google.android.gms.maps.model.PatternItem
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.MapType
import com.google.maps.android.compose.MapsComposeExperimentalApi
import com.google.maps.android.compose.Polygon
import com.google.maps.android.compose.rememberCameraPositionState
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class, MapsComposeExperimentalApi::class)
@Composable
@Preview
actual fun MapView() {
    val pribram = LatLng(49.6883308, 14.0090864)
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(pribram, 10f)
    }
    cameraPositionState.position = CameraPosition.fromLatLngZoom(pribram, 15f)

    var title by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var url by remember { mutableStateOf("") }
    var zoneSelected by remember { mutableStateOf(false) }

    val parkingSlots = ParkingScreenModel().parseXml()

    val scaffoldState =
        rememberBottomSheetScaffoldState(rememberStandardBottomSheetState(skipHiddenState = false))
    val coroutineScope = rememberCoroutineScope()

    val hideModalBottomSheet: () -> Unit =
        { coroutineScope.launch { async { scaffoldState.bottomSheetState.hide() }.await() } }
    val expandModalBottomSheet: () -> Unit =
        { coroutineScope.launch { async { scaffoldState.bottomSheetState.expand() }.await() } }

    val uriHandler = LocalUriHandler.current

    val PATTERN_DASH_LENGTH_PX = 20
    val DASH: PatternItem = Dash(PATTERN_DASH_LENGTH_PX.toFloat())
    val GAP: PatternItem = Gap(20f)
    val PATTERN_POLYGON_ALPHA = listOf(GAP, DASH)

    /*MapEffect { map ->
        val layer = KmlLayer(map, R.raw.parking_slots_data, MainActivity().getContext())
        layer.addLayerToMap()
    }*/

    BottomSheetScaffold(
        sheetSwipeEnabled = false,
        scaffoldState = scaffoldState,
        sheetPeekHeight = 0.dp,
        sheetDragHandle = {},
        sheetContent = {
            Column(
                modifier = Modifier.padding(start = 10.dp, end = 10.dp, bottom = 10.dp),
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        title,
                        style = MaterialTheme.typography.headlineSmall
                    )
                    IconButton(
                        onClick = {
                            hideModalBottomSheet()
                            zoneSelected = false
                        },
                        content = {
                            Icon(
                                imageVector = Icons.Outlined.Close,
                                contentDescription = "Close button"
                            )
                        }
                    )
                }

                Text(
                    description,
                    style = MaterialTheme.typography.bodyLarge
                )
                Button(
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally),
                    onClick = {
                        uriHandler.openUri(url.trimIndent())
                        //startActivity(browserIntent)
                    },
                    content = {
                        Text("Zaplatit")
                    }
                )
            }
        }
    ) {
        GoogleMap(
            modifier = Modifier.fillMaxSize(),
            properties = MapProperties(
                isTrafficEnabled = true,
                mapType = MapType.NORMAL,
                mapStyleOptions = MapStyleOptions(
                    if (isSystemInDarkTheme()) {
                        MapStyle.JSON_DARK
                    } else MapStyle.JSON_LIGHT
                )
                /*latLngBoundsForCameraTarget = LatLngBounds(
                    LatLng(49.7195186,13.9845228)) */
            ),
            cameraPositionState = cameraPositionState
        ) {
            parkingSlots.forEach { parkingZone ->
                parkingZone.parkingSlotPolygons.forEach { latLngList ->
                    Polygon(
                        points = latLngList,
                        fillColor = if (zoneSelected && parkingZone.name == title) parkingZone.color.copy(
                            alpha = 0.7f
                        ) else parkingZone.color.copy(alpha = 0.3f),
                        strokeColor = parkingZone.color.copy(0.9f),
                        strokeWidth = 1f,
                        //strokePattern = if(zone_selected) {PATTERN_POLYGON_ALPHA} else {} ,

                        onClick = {
                            coroutineScope.launch {
                                cameraPositionState.animate(
                                    update = CameraUpdateFactory.newCameraPosition(
                                        CameraPosition(
                                            getLalLngTarget(latLngList),
                                            17f, 0f, 0f
                                        )
                                    ),
                                    durationMs = 1000
                                )
                            }

                            if (zoneSelected) {
                                coroutineScope.launch {
                                    scaffoldState.bottomSheetState.hide()
                                    scaffoldState.bottomSheetState.expand()
                                }
                            } else {
                                expandModalBottomSheet()
                                zoneSelected = true
                            }

                            title = parkingZone.name
                            description = parkingZone.description
                            url = parkingZone.paymentUrl
                        },
                        clickable = true
                    )
                }
            }
        }
    }
}


fun getLalLngTarget(points: List<LatLng>): LatLng {
    var latitude = 0.0
    var longitude = 0.0
    val n = points.size

    for (point in points) {
        latitude += point.latitude
        longitude += point.longitude
    }

    return LatLng(latitude / n, longitude / n)
}
