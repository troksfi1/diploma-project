import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.navigator.tab.CurrentTab
import cafe.adriel.voyager.navigator.tab.LocalTabNavigator
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabNavigator
import presentation.composables.tabs.EventsTab
import presentation.composables.tabs.HomeTab
import presentation.composables.tabs.MoreTab
import presentation.composables.tabs.ParkingTab
import presentation.theme.AppTheme

@Composable
fun App() {
    AppTheme {
        TabNavigator(
            tab = HomeTab
        ) {
            Scaffold(
                modifier = Modifier.fillMaxSize(),
                bottomBar = {
                    BottomNavigation(
                        backgroundColor = MaterialTheme.colorScheme.background
                    ) {
                        TabNavigationItem(HomeTab)
                        TabNavigationItem(EventsTab)
                        TabNavigationItem(ParkingTab)
                        TabNavigationItem(MoreTab)
                    }
                },
                content = { CurrentTab() },
            )
        }
    }
}

@Composable
private fun RowScope.TabNavigationItem(tab: Tab) {
    val tabNavigator: TabNavigator = LocalTabNavigator.current

    val selected = tabNavigator.current == tab

    BottomNavigationItem(
        selected = tabNavigator.current == tab,
        onClick = { tabNavigator.current = tab },
        icon = {
            tab.options.icon?.let { icon ->
                Icon(
                    tint = if (selected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.secondary,
                    painter = icon,
                    contentDescription = tab.options.title
                )
            }
        },
        label = {
            Text(
                text = tab.options.title,
                color = if (selected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.secondary

            )
        }
    )
}