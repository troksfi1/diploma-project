package presentation.screens.more

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.currentOrThrow
import presentation.composables.ImageButton

class MoreScreen : Screen {

    @Composable
    override fun Content() {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            val navigator: Navigator = LocalNavigator.currentOrThrow

            //todo extract icon, set font, set background color
            LazyVerticalGrid(
                columns = GridCells.Adaptive(minSize = 140.dp)
            ) {
                items(18) { photo ->
                    ImageButton("gg",
                        "drawable/img_municipal_authority.png",
                        200
                    )
                    //PhotoItem(photo)
                }
            }

        }
    }
}
