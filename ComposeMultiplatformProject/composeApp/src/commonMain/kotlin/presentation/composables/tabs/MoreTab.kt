package presentation.composables.tabs

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Menu
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabOptions
import cafe.adriel.voyager.transitions.SlideTransition
import presentation.screens.more.MoreScreen

object MoreTab : Tab {

    override val options: TabOptions
        @Composable
        get() {
            val title = "Více"
            val icon = rememberVectorPainter(Icons.Outlined.Menu)

            return remember {
                TabOptions(
                    index = 1u,
                    title = title,
                    icon = icon
                )
            }
        }

    @Composable
    override fun Content() {
        Navigator(screen = MoreScreen()) { navigator ->
            SlideTransition(navigator = navigator)
        }
    }
}
