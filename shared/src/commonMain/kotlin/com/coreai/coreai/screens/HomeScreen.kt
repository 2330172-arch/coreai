package com.coreai.coreai.screens

import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.runtime.getValue

private val FondoOscuro = Color(0xFF081018)
private val AzulNeon = Color(0xFF00E5FF)
private val RojoEmergencia = Color(0xFFD50000)
private val CardColor = Color(0xFF151C24)

@Composable
fun CoreOrb() {
    val infiniteTransition = rememberInfiniteTransition()

    val radius = infiniteTransition.animateFloat(
        initialValue = 70f,
        targetValue = 95f,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = 1800,
                easing = FastOutSlowInEasing
            ),
            repeatMode = RepeatMode.Reverse
        )
    )

    Box(
        modifier = Modifier.size(220.dp),
        contentAlignment = Alignment.Center
    ) {
        Canvas(
            modifier = Modifier.fillMaxSize()
        ) {
            drawCircle(
                color = AzulNeon.copy(alpha = 0.15f),
                radius = radius.value * 2f
            )

            drawCircle(
                color = AzulNeon.copy(alpha = 0.25f),
                radius = radius.value * 1.5f,
                style = Stroke(width = 4f)
            )

            drawCircle(
                color = AzulNeon,
                radius = radius.value
            )

            drawCircle(
                color = Color.White,
                radius = 12f
            )
        }
    }
}

@Composable
fun HomeScreen(
    abrirRecordatorios: () -> Unit,
    abrirEmergencia: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(FondoOscuro)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "CORE AI",
                    color = AzulNeon,
                    fontSize = 28.sp
                )
                Text(
                    text = "⚙",
                    color = AzulNeon,
                    fontSize = 24.sp
                )
            }

            Spacer(modifier = Modifier.height(20.dp))

            Text(
                text = "Sistema Activo",
                color = AzulNeon
            )

            Spacer(modifier = Modifier.height(25.dp))

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(400.dp),
                colors = CardDefaults.cardColors(containerColor = CardColor),
                shape = RoundedCornerShape(20.dp)
            ) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        CoreOrb()
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = "CORE",
                            color = Color.White,
                            fontSize = 32.sp
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = "ASISTENTE ACTIVO",
                            color = AzulNeon
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            Button(
                onClick = abrirEmergencia,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp),
                colors = ButtonDefaults.buttonColors(containerColor = RojoEmergencia)
            ) {
                Text("✱ Emergencia")
            }

            Spacer(modifier = Modifier.height(12.dp))

            Button(
                onClick = abrirRecordatorios,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp)
            ) {
                Text("🔔 Recordatorios")
            }

            Spacer(modifier = Modifier.height(20.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Card(
                    modifier = Modifier.weight(1f),
                    colors = CardDefaults.cardColors(containerColor = CardColor)
                ) {
                    Column(modifier = Modifier.padding(12.dp)) {
                        Text(text = "Próxima alerta", color = AzulNeon)
                        Text(text = "14:30")
                        Text(text = "Medicamento")
                    }
                }

                Card(
                    modifier = Modifier.weight(1f),
                    colors = CardDefaults.cardColors(containerColor = CardColor)
                ) {
                    Column(modifier = Modifier.padding(12.dp)) {
                        Text(text = "Estado red", color = AzulNeon)
                        Text(text = "Óptimo")
                        Text(text = "12 ms")
                    }
                }
            }

            Spacer(modifier = Modifier.weight(1f))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                Text("🎤", fontSize = 24.sp)
                Text("🕒", fontSize = 24.sp)
                Text("🚨", fontSize = 24.sp)
                Text("👤", fontSize = 24.sp)
            }

            Spacer(modifier = Modifier.height(10.dp))
        }
    }
}