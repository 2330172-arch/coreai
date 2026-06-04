package com.coreai.coreai.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

private val FondoEmergency = Color(0xFF081018)
private val AzulEmergency = Color(0xFF00E5FF)
private val CardEmergency = Color(0xFF151C24)
private val RojoSOS = Color(0xFFD50000)

@Composable
fun EmergencyScreen() {

    var gpsEnabled by remember { mutableStateOf(true) }
    var audioEnabled by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(_root_ide_package_.com.coreai.coreai.screens.FondoEmergency)
            .padding(16.dp)
    ) {

        // ENCABEZADO

        Text(
            text = "CORE AI",
            color = _root_ide_package_.com.coreai.coreai.screens.AzulEmergency,
            fontSize = 30.sp
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Sección de Emergencia",
            color = Color.White,
            fontSize = 24.sp
        )

        Text(
            text = "Sistema monitoreado activamente",
            color = _root_ide_package_.com.coreai.coreai.screens.AzulEmergency
        )

        Spacer(modifier = Modifier.height(25.dp))

        // TARJETA PRINCIPAL

        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(
                containerColor = _root_ide_package_.com.coreai.coreai.screens.CardEmergency
            ),
            shape = RoundedCornerShape(16.dp)
        ) {

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Text(
                    text = "ACCIÓN CRÍTICA",
                    color = Color.Red
                )

                Spacer(modifier = Modifier.height(16.dp))

                Button(
                    onClick = {
                        // Aquí después conectaremos el SOS real
                    },
                    modifier = Modifier
                        .width(180.dp)
                        .height(180.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = _root_ide_package_.com.coreai.coreai.screens.RojoSOS
                    ),
                    shape = RoundedCornerShape(20.dp)
                ) {

                    Text(
                        text = "✱\n\nSOS",
                        fontSize = 28.sp
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = "Presiona para alertar a todos los contactos inmediatamente",
                    color = Color.White
                )
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        // LOCALIZACIÓN

        Card(
            colors = CardDefaults.cardColors(
                containerColor = _root_ide_package_.com.coreai.coreai.screens.CardEmergency
            )
        ) {

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),

                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {

                Text(
                    text = "📍 Localización en tiempo real",
                    color = Color.White
                )

                Switch(
                    checked = gpsEnabled,
                    onCheckedChange = {
                        gpsEnabled = it
                    }
                )
            }
        }

        Spacer(modifier = Modifier.height(10.dp))

        // AUDIO

        Card(
            colors = CardDefaults.cardColors(
                containerColor = _root_ide_package_.com.coreai.coreai.screens.CardEmergency
            )
        ) {

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),

                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {

                Text(
                    text = "🎤 Enviar audio automático",
                    color = Color.White
                )

                Switch(
                    checked = audioEnabled,
                    onCheckedChange = {
                        audioEnabled = it
                    }
                )
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        // CONTACTOS

        Text(
            text = "CONTACTOS DE EMERGENCIA",
            color = _root_ide_package_.com.coreai.coreai.screens.AzulEmergency
        )

        Spacer(modifier = Modifier.height(10.dp))

        _root_ide_package_.com.coreai.coreai.screens.ContactCard("Mamá")
        _root_ide_package_.com.coreai.coreai.screens.ContactCard("Papá")
        _root_ide_package_.com.coreai.coreai.screens.ContactCard("Novia")
    }
}

@Composable
fun ContactCard(nombre: String) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 8.dp),

        colors = CardDefaults.cardColors(
            containerColor = _root_ide_package_.com.coreai.coreai.screens.CardEmergency
        )
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),

            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            Text(
                text = nombre,
                color = Color.White
            )

            Text(
                text = "📞",
                color = _root_ide_package_.com.coreai.coreai.screens.AzulEmergency
            )
        }
    }
}