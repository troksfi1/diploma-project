package cz.cvut.fit.nidip.troksfil.presentation.screens.more

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.BottomNavigation
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cz.cvut.fit.nidip.troksfil.domain.ImageButtonType
import cz.cvut.fit.nidip.troksfil.presentation.components.ImageButton

class MoreScreen : Screen {
    val listOfPages = listOf(
        ImageButtonType.DEFECTS,
        ImageButtonType.MUNICIPAL_AUTHORITY,
        ImageButtonType.SERVICES,
        ImageButtonType.FACEBOOK,
        ImageButtonType.WEB,
        ImageButtonType.WEBCAMS,
    )

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
                LazyVerticalGrid(
                    columns = GridCells.Adaptive(minSize = 140.dp),
                ) {
                    items(listOfPages) { page ->
                        Box(modifier = Modifier.padding(10.dp)) {
                            ImageButton(
                                page,
                                imageSize = 150
                            )
                        }
                    }
                }
            }
        }
    }
}
