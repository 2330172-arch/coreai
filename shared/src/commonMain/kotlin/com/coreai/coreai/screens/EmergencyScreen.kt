package com.coreai.coreai.screens
import com.coreai.coreai.network.EmergencyService
import com.coreai.coreai.models.Emergency
import com.coreai.coreai.repository.EmergencyRepository
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch
import kotlin.random.Random






// 4. PANTALLA DE INTERFAZ DE USUARIO CON INTERACCIÓN REFORZADA
@Composable
fun EmergencyScreen() {
    val scope = rememberCoroutineScope()
    val repository = remember { EmergencyRepository() }

    var systemStatus by remember { mutableStateOf("Activo") }
    var realTimeLocation by remember { mutableStateOf(true) }
    var autoAudio by remember { mutableStateOf(true) }
    var alertConfirmation by remember { mutableStateOf("") }

    val contacts = listOf("Mama", "Papa", "Hermano")
    var historyList by remember { mutableStateOf(repository.getLocalHistory()) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("CORE AI", fontSize = 28.sp, fontWeight = FontWeight.Bold, color = Color(0xFF6200EE))
        Text("SECCION DE EMERGENCIA", fontSize = 14.sp, color = Color.Gray)

        Spacer(modifier = Modifier.height(24.dp))

        // BOTÓN SOS CON CAPA DE CLICK EXPLICITA Y RESPUESTA VISUAL
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .size(140.dp)
                .clip(CircleShape)
                .background(Color.Red)
                .clickable {
                    val newEmergency = Emergency(
                        id = Random.nextInt(100, 999),
                        usuario = "Carlos",
                        mensaje = "Necesito ayuda urgente - SOS",
                        fecha = "2026-06-08"
                    )

                    scope.launch {
                        val success = repository.sendAndSaveEmergency(newEmergency)
                        historyList = repository.getLocalHistory()

                        alertConfirmation = if (success) {
                            "¡Alerta SOS despachada con exito!"
                        } else {
                            "Alerta resguardada en cache local."
                        }
                    }
                }
        ) {
            Text("SOS", color = Color.White, fontSize = 32.sp, fontWeight = FontWeight.Bold)
        }

        Spacer(modifier = Modifier.height(16.dp))
        Text("Estado de Sensores: $systemStatus", fontSize = 16.sp, fontWeight = FontWeight.Medium)
        Spacer(modifier = Modifier.height(16.dp))

        Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp)) {
            Checkbox(checked = realTimeLocation, onCheckedChange = { realTimeLocation = it })
            Text("Ubicación en tiempo real habilitada", fontSize = 14.sp)
        }
        Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp)) {
            Checkbox(checked = autoAudio, onCheckedChange = { autoAudio = it })
            Text("Grabación de audio automática", fontSize = 14.sp)
        }

        if (alertConfirmation.isNotEmpty()) {
            Card(
                colors = CardDefaults.cardColors(containerColor = Color(0xFFE8F5E9)),
                modifier = Modifier.padding(vertical = 12.dp).fillMaxWidth()
            ) {
                Text(
                    text = alertConfirmation,
                    color = Color(0xFF2E7D32),
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(12.dp).align(Alignment.CenterHorizontally)
                )
            }
        }

        Spacer(modifier = Modifier.height(8.dp))
        HorizontalDivider(thickness = 1.dp, color = Color.LightGray)
        Spacer(modifier = Modifier.height(8.dp))

        Text("Contactos de Red Asignados:", fontWeight = FontWeight.Bold, modifier = Modifier.align(Alignment.Start))
        contacts.forEach { contact ->
            Text("• $contact", modifier = Modifier.align(Alignment.Start).padding(start = 8.dp))
        }

        Spacer(modifier = Modifier.height(16.dp))
        Text("Historial de Alertas Emitidas (LazyColumn):", fontWeight = FontWeight.Bold, modifier = Modifier.align(Alignment.Start))

        LazyColumn(modifier = Modifier.fillMaxWidth().weight(1f)) {
            items(historyList) { item ->
                Card(
                    modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp),
                    colors = CardDefaults.cardColors(containerColor = Color(0xFFF5F5F5))
                ) {
                    Column(modifier = Modifier.padding(12.dp)) {
                        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                            Text("ID Tráfico: #${item.id}", fontSize = 11.sp, color = Color.Gray)
                            Text("Fecha: ${item.fecha}", fontSize = 11.sp, color = Color.Gray)
                        }
                        Spacer(modifier = Modifier.height(4.dp))
                        Text("Origen: ${item.usuario}", fontWeight = FontWeight.Bold, fontSize = 14.sp)
                        Text("Payload: ${item.mensaje}", fontSize = 13.sp)
                    }
                }
            }
        }
    }
}