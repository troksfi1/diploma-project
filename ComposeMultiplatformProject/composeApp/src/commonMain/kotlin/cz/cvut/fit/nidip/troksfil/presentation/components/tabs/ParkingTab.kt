@file:OptIn(ExperimentalAnimationApi::class)

package cz.cvut.fit.nidip.troksfil.presentation.components.tabs

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.DirectionsCar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabOptions
import cafe.adriel.voyager.transitions.SlideTransition
import cz.cvut.fit.nidip.troksfil.presentation.screens.parking.ParkingScreen

object ParkingTab : Tab {

    override val options: TabOptions
        @Composable
        get() {
            val title = "Parkování"
            val icon = rememberVectorPainter(Icons.Outlined.DirectionsCar)

            return remember {
                TabOptions(
                    index = 0u,
                    title = title,
                    icon = icon
                )
            }
        }

    @Composable
    override fun Content() {
        Navigator(screen = ParkingScreen()) { navigator ->
            SlideTransition(navigator = navigator)
        }
    }
}
