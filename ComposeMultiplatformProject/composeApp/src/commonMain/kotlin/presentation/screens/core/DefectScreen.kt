package presentation.screens.core

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.outlined.PhotoCamera
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.currentOrThrow

class DefectScreen : Screen {

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    override fun Content() {

        val navigator: Navigator = LocalNavigator.currentOrThrow

        Scaffold(
            topBar = {
                TopAppBar(
                    title = { /*Text(event.title, fontSize = 15.sp)*/ },
                    navigationIcon = {
                        Button(onClick = { navigator.pop() }) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                contentDescription = "Back",
                            )
                        }
                    }
                )
            },
            bottomBar = {

            }
        ) {
            var defectTitle by remember { mutableStateOf("") }
            var defectDescription by remember { mutableStateOf("") }
            val scrollState = rememberScrollState()
            Column(
                modifier = Modifier
                    .verticalScroll(state = scrollState)
                    .background(MaterialTheme.colorScheme.surface)
            ) {
                FilledIconButton(onClick = {/* open image picker*/ }, content = {})

                Column(
                    modifier = Modifier
                        .padding(10.dp),
                    horizontalAlignment = Alignment.CenterHorizontally

                ) {
                    Text("Záznam o závadě", style = MaterialTheme.typography.headlineLarge)
                    Divider(thickness = 20.dp, color = MaterialTheme.colorScheme.background)
                    TextField(
                        value = defectTitle,
                        label = { Text("Zadejte název závady") },
                        onValueChange = { defectTitle = it  /* onTextChanged(it)*/ },
                        modifier = Modifier
                            .fillMaxSize()
                    )
                    Divider(thickness = 20.dp, color = MaterialTheme.colorScheme.background)
                    FilledTonalButton(
                        modifier = Modifier.size(150.dp, 150.dp),
                        onClick = {/* vyberte misto z mapy*/ }) {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Icon(Icons.Outlined.PhotoCamera, contentDescription = "add photo")
                            Text(
                                "Přidat fotografii závady",
                                textAlign = TextAlign.Center
                            )
                        }
                    }
                    Divider(thickness = 20.dp, color = MaterialTheme.colorScheme.background)
                    TextField(
                        value = defectDescription,
                        label = { Text("Zadejte popis závady") },
                        onValueChange = { defectDescription = it  /* onTextChanged(it)*/ },
                        modifier = Modifier.fillMaxSize()
                    )
                    Divider(thickness = 20.dp, color = MaterialTheme.colorScheme.background)

                    val defectTypes =
                        arrayOf("Pozemní komunikace", "Dopravní značení")
                    var expanded by remember { mutableStateOf(false) }
                    var selectedText by remember { mutableStateOf(defectTypes[0]) }

                    ExposedDropdownMenuBox(
                        expanded = expanded,
                        onExpandedChange = {
                            expanded = !expanded
                        }
                    ) {
                        TextField(
                            value = selectedText,
                            onValueChange = {},
                            readOnly = true,
                            label = { Text("Vyberte typ závady") },
                            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
                            modifier = Modifier.menuAnchor().fillMaxWidth()
                        )

                        ExposedDropdownMenu(
                            expanded = expanded,
                            onDismissRequest = { expanded = false }
                        ) {
                            defectTypes.forEach { item ->
                                DropdownMenuItem(
                                    text = { Text(text = item) },
                                    onClick = {
                                        selectedText = item
                                        expanded = false
                                    }
                                )
                            }
                        }
                    }
                    Divider(thickness = 20.dp, color = MaterialTheme.colorScheme.background)

                    Button(onClick = { navigator.pop() }) {
                        Text("Odeslat závadu")
                    }
                }
            }
        }
    }

}