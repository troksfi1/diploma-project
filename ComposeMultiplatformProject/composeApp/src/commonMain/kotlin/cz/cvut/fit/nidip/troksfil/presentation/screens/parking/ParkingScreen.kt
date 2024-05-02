package cz.cvut.fit.nidip.troksfil.presentation.screens.parking

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.BottomNavigation
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.LocalParking
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.currentOrThrow

class ParkingScreen : Screen {

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    override fun Content() {
        //val screenmodel = getScreenModel<ParkingScreenModel>()

        //val screenModel = getScreenModel<ParkingScreenModel>()
        //val screenModel = getScreenModel<ParkingScreenModel>()
        //val state by screenModel.state.collectAsState()

        val navigator: Navigator = LocalNavigator.currentOrThrow
        var text by remember { mutableStateOf("") }
        var active by remember { mutableStateOf(false) }
        var parkingZoneNames = remember {
            mutableListOf(
                "nám. T.G.M. [PB1]",
                "Osvobození [PB2]",
                "nám. 17. listopadu [PB3]",
                "Václavské náměstí [PB4]",
                "Zahradnická [PB5]",
                "Generála Tesaříka [PB6]",
                "Jiráskovy sady [PB7]",
                "Hornícké náměstí [PB8]",
                "Dvořákovo nábřeží [PB9]",
                "Okolo Zámečku [PB10]",
                "Jinecká na Hvězdičce [PB12]",
                "Fibichova [PB14]",
                "Nový Rybník [PB16]",
                "Parkoviště u nemocnice",
                "Parkoviště u hřbitova",
            )
        }
        var parkingZoneNamesToShow = parkingZoneNames
        var selectedRow by remember { mutableStateOf("") }
        val scrollState = rememberScrollState()

        Scaffold(
            bottomBar = { BottomNavigation {} }
        ) { innerPadding ->
            Box(
                modifier = Modifier.padding(PaddingValues(bottom = innerPadding.calculateBottomPadding()))
            ) {
                SearchBar(
                    modifier = Modifier
                        .fillMaxWidth(),
                    query = text,
                    onQueryChange = {
                        text = it
                        parkingZoneNamesToShow =
                            parkingZoneNames.filter { zone -> zone.contains(text, true) }
                                .toMutableList()
                    },
                    onSearch = {
                        active = false
                        println("find zone $text")
                        moveMap(text) //todo
                        //state.searchedZoneName = text
                    },
                    active = active,
                    onActiveChange = {
                        active = it
                    },
                    placeholder = {
                        Text(text = "Zadejte název nebo číslo zóny")
                    },
                    leadingIcon = { Icon(Icons.Filled.Search, "Search Icon") },
                    trailingIcon = {
                        if (active) {
                            Icon(
                                modifier = Modifier.clickable {
                                    if (text.isNotEmpty()) {
                                        text = ""
                                    } else {
                                        active = false
                                    }
                                },
                                imageVector = Icons.Filled.Close,
                                contentDescription = "Close Icon"
                            )
                        }
                    }
                ) {
                    Column(
                        modifier = Modifier.verticalScroll(scrollState, enabled = true)
                    ) {
                        parkingZoneNamesToShow.forEach { zoneName ->
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clickable {
                                        selectedRow = zoneName
                                        text = zoneName
                                        active = false
                                        println("find zone $text")
                                        moveMap(text) //todo
                                        //state.searchedZoneName = text
                                    }
                            ) {
                                Row(
                                    modifier = Modifier
                                        .padding(all = 15.dp)
                                ) {
                                    Icon(
                                        modifier = Modifier.padding(end = 10.dp),
                                        imageVector = Icons.Default.LocalParking,
                                        contentDescription = "Parking zone icon"
                                    )
                                    Text(text = zoneName)
                                }
                            }
                        }
                    }
                }
                MapView()
            }
        }
    }
}

