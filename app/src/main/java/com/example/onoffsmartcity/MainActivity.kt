package com.example.onoffsmartcity

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.onoffsmartcity.ui.theme.ONOFFSMARTCITYTheme
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

// Nama dan NIM
// MOCHAMAD BAHRUL NGULUM (211112113)
// FITROTIN KARIMAH (211112146)

class MainActivity : ComponentActivity() {

    private lateinit var databaseReference: DatabaseReference
    private lateinit var auth: FirebaseAuth

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        // Inisialisasi Firebase Auth
        auth = FirebaseAuth.getInstance()

        // Autentikasi anonim
        auth.signInAnonymously()
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Autentikasi berhasil
                    Log.d("MainActivity", "signInAnonymously:success")
                    // Inisialisasi Firebase Database setelah autentikasi berhasil
                    databaseReference = FirebaseDatabase.getInstance().getReference("Relay")

                    // Set content setelah autentikasi berhasil
                    setContent {
                        ONOFFSMARTCITYTheme {
                            var relayStatus by remember { mutableStateOf("") }

                            Scaffold(
                                topBar = {
                                    TopAppBar(
                                        title = {
                                            Text(
                                                text = "ON/OFF SMART CITY",
                                                style = MaterialTheme.typography.headlineLarge
                                            )
                                        }
                                    )
                                },
                                modifier = Modifier
                                    .fillMaxSize()
                                    .background(
                                        brush = Brush.verticalGradient(
                                            colors = listOf(Color(0xFFE0F7FA), Color(0xFFB2EBF2))
                                        )
                                    )
                            ) { innerPadding ->
                                Column(
                                    modifier = Modifier
                                        .padding(innerPadding)
                                        .fillMaxSize(),
                                    horizontalAlignment = Alignment.CenterHorizontally
                                ) {
                                    Spacer(modifier = Modifier.height(16.dp))
                                    Text(
                                        text = "MOCHAMAD BAHRUL NGULUM (211112113)\nFITROTIN KARIMAH (211112146)",
                                        style = MaterialTheme.typography.bodyLarge.copy(
                                            fontWeight = FontWeight.Bold,
                                            fontSize = 16.sp
                                        ),
                                        textAlign = TextAlign.Center,
                                        modifier = Modifier.padding(16.dp)
                                    )
                                    ControlUI(
                                        onOnClicked = {
                                            sendValueToFirebase(1)
                                            relayStatus = "Relay ON"
                                        },
                                        onOffClicked = {
                                            sendValueToFirebase(0)
                                            relayStatus = "Relay OFF"
                                        },
                                        relayStatus = relayStatus
                                    )
                                }
                            }
                        }
                    }
                } else {
                    // Autentikasi gagal
                    Log.w("MainActivity", "signInAnonymously:failure", task.exception)
                }
            }
    }

    private fun sendValueToFirebase(value: Int) {
        databaseReference.setValue(value)
    }
}

@Composable
fun ControlUI(modifier: Modifier = Modifier, onOnClicked: () -> Unit, onOffClicked: () -> Unit, relayStatus: String) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(
            onClick = { onOnClicked() },
            colors = ButtonDefaults.buttonColors(Color(0xFF4CAF50)),
            modifier = Modifier
                .padding(16.dp)
                .width(200.dp)
                .height(60.dp),
            shape = MaterialTheme.shapes.medium
        ) {
            Text(text = "ON", fontSize = 20.sp, color = Color.White)
        }
        Button(
            onClick = { onOffClicked() },
            colors = ButtonDefaults.buttonColors(Color(0xFFF44336)),
            modifier = Modifier
                .padding(16.dp)
                .width(200.dp)
                .height(60.dp),
            shape = MaterialTheme.shapes.medium
        ) {
            Text(text = "OFF", fontSize = 20.sp, color = Color.White)
        }
        Text(
            text = relayStatus,
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.padding(top = 16.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ControlUIPreview() {
    ONOFFSMARTCITYTheme {
        ControlUI(onOnClicked = {}, onOffClicked = {}, relayStatus = "")
    }
}
