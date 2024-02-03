package ui.screens.more

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.currentOrThrow

class DetailScreen : Screen {

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    override fun Content() {

        val navigator: Navigator = LocalNavigator.currentOrThrow

        LazyHorizontalGrid(
            rows = GridCells.Adaptive(minSize = 128.dp)
        ) {

        }


        /*Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text("Detail") },
                    navigationIcon = {
                        Button(onClick = { navigator.pop() }) {
                            Icon(
                                imageVector = Icons.Default.ArrowBack,
                                contentDescription = "Back",
                            )
                        }
                    }
                )
            }
        ) { innerPadding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
                    .padding(innerPadding)
            ) {
                Text(
                    text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. " +
                            "Sed non risus. Suspendisse lectus tortor, dignissim sit amet, " +
                            "adipiscing nec, ultricies sed, dolor. Cras elementum ultrices diam. " +
                            "Maecenas ligula massa, varius a, semper congue, euismod non, mi. " +
                            "Proin porttitor, orci nec nonummy molestie, enim est eleifend mi, " +
                            "non fermentum diam nisl sit amet erat. Duis semper. Duis arcu massa, " +
                            "scelerisque vitae, consequat in, pretium a, enim. Pellentesque congue. " +
                            "Ut in risus volutpat libero pharetra tempor. Cras vestibulum bibendum " +
                            "augue. Praesent egestas leo in pede. Praesent blandit odio eu enim. " +
                            "Pellentesque sed dui ut augue blandit sodales. Vestibulum ante ipsum " +
                            "primis in faucibus orci luctus et ultrices posuere cubilia Curae; " +
                            "Aliquam nibh. Mauris ac mauris sed pede pellentesque fermentum. " +
                            "Maecenas adipiscing ante non diam sodales hendrerit.",
                )
            }
        }*/
    }
}
