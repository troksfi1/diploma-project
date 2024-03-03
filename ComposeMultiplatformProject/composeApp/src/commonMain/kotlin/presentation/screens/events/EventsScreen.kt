package presentation.screens.events

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowColumn
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.CalendarMonth
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DateRangePicker
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDateRangePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.DialogProperties
import cafe.adriel.voyager.core.model.rememberScreenModel
import cafe.adriel.voyager.core.screen.Screen
import data.repository.FakeEventsDataSourceImpl
import domain.FilterOption
import presentation.components.EventLazyRow

class EventsScreen : Screen {
    @OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
    @Composable
    override fun Content() {
        val screenModel = rememberScreenModel {
            EventsScreenModel(eventsDataSource = FakeEventsDataSourceImpl())
        }
        val state by screenModel.state.collectAsState()

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

                FilterChip(
                    onClick = { screenModel.onEvent(EventsEvent.OnFilterTodayIsSelected) },
                    label = {
                        Text(text = FilterOption.TODAY.filterName)
                    },
                    selected = FilterOption.TODAY == state.selectedFilterOption
                )

                Spacer(modifier = Modifier.width(10.dp))

                FilterChip(
                    onClick = { screenModel.onEvent(EventsEvent.OnFilterTomorrowIsSelected) },
                    label = {
                        Text(text = FilterOption.TOMORROW.filterName)
                    },
                    selected = FilterOption.TOMORROW == state.selectedFilterOption
                )

                Spacer(modifier = Modifier.width(10.dp))

                FilterChip(
                    onClick = { screenModel.onEvent(EventsEvent.OnFilterDateIsSelected) },
                    label = {
                        Icon(
                            Icons.Outlined.CalendarMonth,
                            contentDescription = "calendar icon"
                        )
                    },
                    selected = FilterOption.SELECTED_DATE == state.selectedFilterOption
                )

                // set the initial date
                val dateRangePickerState =
                    rememberDateRangePickerState()  //todo move state to state class

                if (state.isDatePickerOpen) {

                    DatePickerDialog(
                        properties = DialogProperties(),
                        onDismissRequest = {
                            screenModel.onEvent(EventsEvent.OnDatePickerDismissRequest)
                        },
                        confirmButton = {
                            TextButton(onClick = {
                                screenModel.onEvent(
                                    EventsEvent.OnDateIsPicked
                                        (
                                        dateRangePickerState.selectedStartDateMillis!!,
                                        dateRangePickerState.selectedEndDateMillis!!
                                    )
                                )      //todo move state to state class
                            }) {
                                Text(text = "Potvdit")
                            }
                        },
                        dismissButton = {
                            TextButton(onClick = {
                                screenModel.onEvent(EventsEvent.OnDatePickerDismissRequest)
                            }) {
                                Text(text = "Zrušit")
                            }
                        }
                    ) {
                        DateRangePicker(
                            modifier = Modifier.height(500.dp).padding(0.dp, 20.dp, 0.dp, 0.dp),
                            state = dateRangePickerState,    //todo move state to state class
                            showModeToggle = false
                        )
                    }
                }
            }

            Text("Od: ${state.selectedDateStart} do: ${state.selectedDateEnd}")

            //todo category dropdown menu

            FlowColumn {
                state.filteredEventsCategories.forEach {
                    EventLazyRow(it, state)
                }
            }
        }
    }
}
