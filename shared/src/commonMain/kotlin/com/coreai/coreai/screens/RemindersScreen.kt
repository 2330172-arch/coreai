package com.coreai.coreai.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.coreai.coreai.models.Reminder
import com.coreai.coreai.network.ReminderService
import kotlinx.coroutines.launch

@Composable
fun RemindersScreen() {

    var reminders by remember {
        mutableStateOf<List<Reminder>>(emptyList())
    }

    val scope = rememberCoroutineScope()

    LaunchedEffect(Unit) {

        scope.launch {

            reminders =
                ReminderService().getReminders()
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        Text(
            text = "Mis Recordatorios",
            fontSize = 24.sp
        )

        Spacer(
            modifier = Modifier.height(16.dp)
        )

        LazyColumn(
            modifier = Modifier.weight(1f)
        ) {

            items(reminders) { reminder ->

                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 6.dp)
                ) {

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        horizontalArrangement =
                            Arrangement.SpaceBetween
                    ) {

                        Column {

                            Text(
                                reminder.titulo
                            )

                            Text(
                                reminder.hora
                            )
                        }

                        Text("Activo")
                    }
                }
            }
        }

        FloatingActionButton(
            onClick = {},
            modifier =
                Modifier.align(Alignment.End)
        ) {
            Text("+")
        }
    }
}