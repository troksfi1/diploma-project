package cz.cvut.fit.nidip.troksfil.presentation.screens.parking

import cafe.adriel.voyager.core.model.ScreenModel
import com.google.android.gms.maps.model.LatLng
import cz.cvut.fit.nidip.troksfil.data.remote.rss.dto.Document
import cz.cvut.fit.nidip.troksfil.domain.util.HtmlToTextUtil
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.serialization.decodeFromString
import nl.adaptivity.xmlutil.XmlDeclMode
import nl.adaptivity.xmlutil.core.XmlVersion
import nl.adaptivity.xmlutil.serialization.XML


class ParkingScreenModel : ScreenModel {

    private val _state = MutableStateFlow(ParkingScreenState())
    val state = _state.asStateFlow()

    /*    fun parseXml() {
            val format = XML {
                xmlVersion = XmlVersion.XML10
                xmlDeclMode = XmlDeclMode.Charset
                indentString = "    "
            }

            val parkingZonesKml = ParkingZonesKml.ALL_PARKING_ZONES

            val document = format.decodeFromString<Document>(parkingZonesKml)

            val parkingZones = mutableListOf<ParkingZone>()

            document.Placemark.forEach { placemark ->
                parkingZones.add(
                    ParkingZone(
                        name = placemark.name,
                        paymentUrl = extractUrl(placemark.description),
                        description = getZoneDescription(placemark.description),
                        color = getColor(placemark.name),
                        parkingSlotPolygons = listOf(placemark.Polygon.coordinates.parseCoordinates())
                    )
                )
            }


            _state.update { currentState ->
                currentState.copy(
                    parkingZones = parkingZones
                )
            }
        }*/


    fun onEvent(event: ParkingScreenEvent) {
        when (event) {

            ParkingScreenEvent.OnParkingSearched -> {

            }

            ParkingScreenEvent.OnParkingZoneClicked -> {

            }
        }
    }

    fun parseXml(): MutableList<ParkingZone> {
        val format = XML {
            xmlVersion = XmlVersion.XML10
            xmlDeclMode = XmlDeclMode.Charset
            indentString = "    "
        }

        val parkingZonesKml = ParkingZonesKml.ALL_PARKING_ZONES

        val document = format.decodeFromString<Document>(parkingZonesKml)

        val parkingZones = mutableListOf<ParkingZone>()

        document.Placemark.forEach { placemark ->
            parkingZones.add(
                ParkingZone(
                    name = placemark.name,
                    paymentUrl = extractUrl(placemark.description),
                    description = getZoneDescription(placemark.description),
                    color = getColor(placemark.name),
                    parkingSlotPolygons = listOf(placemark.Polygon.coordinates.parseCoordinates())
                )
            )
        }

        ParkingScreenState().parkingZones = listOf()

        return parkingZones
    }

    private fun getZoneDescription(description: String): String {
        val desc = description.replace(extractUrl(description) + "<br><br>", "")
            .replace("Platba:", "")
            .replace("<br>", "\n")

        return HtmlToTextUtil(desc)
    }

    private fun extractUrl(description: String): String {
        val linkPattern = Regex(
            "(?:^|\\W)((ht|f)tp(s?)://|www\\.)"
                    + "(([\\w\\-]+\\.){1,}?([\\w\\-.~]+/?)*"
                    + "[\\p{Alnum}.,%_=?&#\\-+()\\[\\]*$~@!:/{};']*)"
        )
        val matchResult = linkPattern.find(description)
        return if (matchResult?.value == null) {
            ""
        } else
            matchResult.value
    }

    private fun getColor(name: String): androidx.compose.ui.graphics.Color {
        if (name.contains("Fibich", ignoreCase = true)) {
            return androidx.compose.ui.graphics.Color.Green.copy(alpha = 0.3F)
        }
        if (name.contains("Rybník", ignoreCase = true)) {
            return androidx.compose.ui.graphics.Color.Yellow.copy(alpha = 0.3F)
        }
        if (name.contains("nemocnice", ignoreCase = true)) {
            return androidx.compose.ui.graphics.Color.Black.copy(alpha = 0.3F)
        }
        if (name.contains("PB1", ignoreCase = true)) {
            return androidx.compose.ui.graphics.Color.Red.copy(alpha = 0.3F)
        }
        if (name.contains("hřbitova", ignoreCase = true)) {
            return androidx.compose.ui.graphics.Color.Black.copy(alpha = 0.3F)
        } else return androidx.compose.ui.graphics.Color.Blue.copy(alpha = 0.3F)
    }
}

private fun String.parseCoordinates(): List<LatLng> {

    val latLng = mutableListOf<LatLng>()

    val coordinatesList = this.lines().map { line ->
        if (line.length > 5) {
            var s = line.trimIndent().split(",")
            s = s.dropLast(1)
            if (s.isNotEmpty()) {
                val (latitude, longitude) = s.map { it.toDouble() }
                latLng.add(LatLng(longitude, latitude))
            }
        }

    }
    return latLng
}

actual fun moveMap(zoneName: String) {
    //_state
    val parkingZones = ParkingScreenModel().parseXml()
    val parkingZone = parkingZones.find { it.name == zoneName }

    if (parkingZone != null) {
        println(parkingZone.name)
    }

    //ParkingScreenEvent.OnParkingSearched(parkingSlot)

}
