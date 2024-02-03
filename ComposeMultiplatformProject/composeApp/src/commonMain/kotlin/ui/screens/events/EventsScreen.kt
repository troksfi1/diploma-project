package ui.screens.events

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.CalendarMonth
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import ui.composables.EventLazyRow
import java.util.Calendar

class EventsScreen : Screen {
    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    override fun Content() {

        val scrollState = rememberScrollState()

        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(state = scrollState),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top,
        ) {
            Row(
                modifier = Modifier.fillMaxWidth().padding(10.dp),
                horizontalArrangement = Arrangement.Center,

                ) {

                val itemsList = listOf("Dnes", "Zítra", "Datum", "")

                var selectedItem by remember {
                    mutableStateOf(itemsList[0]) // initially, first item is selected
                }

                val calendar = Calendar.getInstance()

                // set the initial date
                val datePickerState =
                    rememberDatePickerState(initialSelectedDateMillis = calendar.timeInMillis)

                //var showDatePicker by remember { mutableStateOf(false) }

                var selectedDate by remember { mutableLongStateOf(calendar.timeInMillis) }

                FilterChip(
                    onClick = { selectedItem = itemsList[0] },
                    label = {
                        Text(text = itemsList[0])
                    },
                    selected = itemsList[0] == selectedItem

                )

                Spacer(modifier = Modifier.width(20.dp))

                FilterChip(
                    onClick = { selectedItem = itemsList[1] },
                    label = {
                        Text(text = itemsList[1])
                    },
                    selected = itemsList[1] == selectedItem
                )

                Spacer(modifier = Modifier.width(20.dp))

                FilterChip(
                    onClick = { selectedItem = itemsList[2] },
                    label = {
                        Icon(Icons.Outlined.CalendarMonth, contentDescription = "")
                    },
                    selected = itemsList[2] == selectedItem
                )

                if (itemsList[2] == selectedItem) {
                    DatePickerDialog(
                        onDismissRequest = {
                            selectedItem = itemsList[3]
                        },
                        confirmButton = {
                            TextButton(onClick = {
                                selectedItem = itemsList[3]
                                selectedDate = datePickerState.selectedDateMillis!!
                            }) {
                                Text(text = "Potvdit")
                            }
                        },
                        dismissButton = {
                            TextButton(onClick = {
                                selectedItem = itemsList[3]
                                //showDatePicker = false
                            }) {
                                Text(text = "Zrušit")
                            }
                        }
                    ) {
                        DatePicker(
                            state = datePickerState
                        )
                    }
                }
            }

            //todo category dropdown menu
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                ) {
            }

            Text(
                "Polulární",
                modifier = Modifier
                    .padding(10.dp)
                    .align(Alignment.Start),
                fontSize = 20.sp
            )
            EventLazyRow()
            Text(
                "Koncerty",
                modifier = Modifier
                    .padding(10.dp)
                    .align(Alignment.Start),
                fontSize = 20.sp
            )
            EventLazyRow()
            Text(
                "Divadlo",
                modifier = Modifier
                    .padding(10.dp)
                    .align(Alignment.Start),
                fontSize = 20.sp
            )
            EventLazyRow()
        }
    }
}
