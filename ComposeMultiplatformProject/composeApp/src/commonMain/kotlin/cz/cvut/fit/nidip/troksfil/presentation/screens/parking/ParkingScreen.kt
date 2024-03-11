package cz.cvut.fit.nidip.troksfil.presentation.screens.parking

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
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
import com.multiplatform.webview.web.WebView
import com.multiplatform.webview.web.rememberWebViewState
import com.multiplatform.webview.web.rememberWebViewStateWithHTMLData

class ParkingScreen : Screen {

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    override fun Content() {

        val navigator: Navigator = LocalNavigator.currentOrThrow
        var text by remember { mutableStateOf("") }

        Column {
            OutlinedTextField(

                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp, 0.dp, 10.dp, 0.dp),
                value = text,
                label = { Text("Zadejte adresu, číslo zóny") },
                leadingIcon = { Icon(Icons.Filled.Search, "searchIcon") },
                onValueChange = { text = it  /* onTextChanged(it)*/ }
            )


            val html = """
                <html>
                    <body>
                    <iframe src ="https://www.google.com/maps/d/embed?mid=1PsJSE5YLHzz5Rgrqg5gBTyT0ADF0NKC6&ehbc=2E312F"></iframe>
                    </body>
                </html>
                """.trimIndent()
            val state3 = rememberWebViewStateWithHTMLData(
                data = html
            )
            val state =
                rememberWebViewState("https://www.google.com/maps/d/viewer?mid=1PsJSE5YLHzz5Rgrqg5gBTyT0ADF0NKC6&femb=1&ll=49.684745630467305%2C14.009664090411311&z=15")
            val state2 =
                rememberWebViewState("https://earth.google.com/web/data=MkYKRApCCiExUHNKU0U1WUxIeno1UmdycWc1Z0JUeVQwQURGME5LQzYSGwoZNjA2NWM5MjlfMjE3RDAwQTY0QUVBRURDMiAC://www.google.com/maps/d/viewer?mid=1PsJSE5YLHzz5Rgrqg5gBTyT0ADF0NKC6&femb=1&ll=49.684745630467305%2C14.009664090411311&z=15")

            WebView(state3)
        }
    }
    /*val singapore = LatLng(1.35, 103.87)
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(singapore, 10f)
    }
    GoogleMap(
    modifier = Modifier.fillMaxSize(),
    cameraPositionState = cameraPositionState
    )*/
}

