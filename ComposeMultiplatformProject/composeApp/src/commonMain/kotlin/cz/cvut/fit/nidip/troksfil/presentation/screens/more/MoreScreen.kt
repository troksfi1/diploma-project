package cz.cvut.fit.nidip.troksfil.presentation.screens.more

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.BottomNavigation
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.currentOrThrow
import cz.cvut.fit.nidip.troksfil.presentation.components.ImageButton

class MoreScreen : Screen {

    @Composable
    override fun Content() {
        Scaffold(
            bottomBar = { BottomNavigation {} }
        ) {
            Column(
                modifier = Modifier.fillMaxSize()
                    .padding(PaddingValues(bottom = it.calculateBottomPadding())),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                val navigator: Navigator = LocalNavigator.currentOrThrow

                //todo extract icon, set font, set background color
                LazyVerticalGrid(
                    columns = GridCells.Adaptive(minSize = 140.dp),

                    //contentPadding = PaddingValues(10.dp)
                ) {
                    items(18) { photo ->
                        Box(modifier = Modifier.padding(10.dp)) {
                            ImageButton("Popis", "drawable/img_municipal_authority.png",
                                imageSize = 150,
                                onClick = {}
                            )
                        }

                        //PhotoItem(photo)
                    }
                }
            }
        }
    }
}
