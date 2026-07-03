package com.coreai.coreai.screens
import com.coreai.coreai.notifications.NotificationManager

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.coreai.coreai.models.Reminder
import com.coreai.coreai.network.ReminderService
import com.coreai.coreai.storage.ReminderStorage
import kotlinx.coroutines.launch

@Composable
fun RemindersScreen() {

    var reminders by remember {
        mutableStateOf<List<Reminder>>(emptyList())
    }

    var showDialog by remember {
        mutableStateOf(false)
    }

    var titulo by remember {
        mutableStateOf("")
    }

    var fecha by remember {
        mutableStateOf("")
    }

    var hora by remember {
        mutableStateOf("")
    }

    var editingId by remember {
        mutableStateOf<Int?>(null)
    }

    val scope = rememberCoroutineScope()

    LaunchedEffect(Unit) {

        scope.launch {

            val downloadedReminders =
                ReminderService().getReminders()

            ReminderStorage.saveReminders(
                downloadedReminders
            )

            reminders =
                ReminderStorage.getReminders()
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
                                text = reminder.titulo
                            )

                            Spacer(
                                modifier = Modifier.height(4.dp)
                            )

                            Text(
                                text = "📅 ${reminder.fecha}"
                            )

                            Text(
                                text = "⏰ ${reminder.hora}"
                            )
                        }

                        Row {

                            Button(
                                onClick = {

                                    editingId =
                                        reminder.id

                                    titulo =
                                        reminder.titulo

                                    fecha =
                                        reminder.fecha

                                    hora =
                                        reminder.hora

                                    showDialog = true
                                }
                            ) {
                                Text("Editar")
                            }

                            Spacer(
                                modifier =
                                    Modifier.width(8.dp)
                            )

                            Button(
                                onClick = {

                                    reminders =
                                        reminders.filter {
                                            it.id != reminder.id
                                        }
                                }
                            ) {
                                Text("Eliminar")
                            }
                        }
                    }
                }
            }
        }

        FloatingActionButton(
            onClick = {

                editingId = null

                titulo = ""
                fecha = ""
                hora = ""

                showDialog = true
            },
            modifier =
                Modifier.align(Alignment.End)
        ) {
            Text("+")
        }
    }

    if (showDialog) {

        AlertDialog(
            onDismissRequest = {
                showDialog = false
            },

            confirmButton = {

                Button(
                    onClick = {

                        if (titulo.isNotBlank()) {

                            if (editingId != null) {

                                reminders =
                                    reminders.map {

                                        if (it.id == editingId) {

                                            it.copy(
                                                titulo = titulo,
                                                fecha = fecha,
                                                hora = hora
                                            )

                                        } else {
                                            it
                                        }
                                    }

                            } else {

                                val newReminder =
                                    Reminder(
                                        id = reminders.size + 1,
                                        titulo = titulo,
                                        fecha = fecha,
                                        hora = hora
                                    )

                                reminders =
                                    reminders + newReminder

                            }

                            titulo = ""
                            fecha = ""
                            hora = ""

                            editingId = null
                            NotificationManager.showNotification(
                                title = "Recordatorio guardado",
                                message = titulo
                            )
                            showDialog = false
                        }
                    }
                ) {
                    Text("Guardar")
                }
            },

            dismissButton = {

                Button(
                    onClick = {
                        showDialog = false
                    }
                ) {
                    Text("Cancelar")
                }
            },

            title = {
                Text("Nuevo Recordatorio")
            },

            text = {

                Column {

                    OutlinedTextField(
                        value = titulo,
                        onValueChange = {
                            titulo = it
                        },
                        label = {
                            Text("Nombre")
                        }
                    )

                    Spacer(
                        modifier = Modifier.height(8.dp)
                    )

                    OutlinedTextField(
                        value = fecha,
                        onValueChange = {
                            fecha = it
                        },
                        label = {
                            Text("Fecha (dd/mm/aaaa)")
                        }
                    )

                    Spacer(
                        modifier = Modifier.height(8.dp)
                    )

                    OutlinedTextField(
                        value = hora,
                        onValueChange = {
                            hora = it
                        },
                        label = {
                            Text("Hora (HH:MM)")
                        }
                    )
                }
            }
        )
    }
}