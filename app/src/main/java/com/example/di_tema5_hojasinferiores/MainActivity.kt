package com.example.di_tema5_hojasinferiores

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ArrowBack
import androidx.compose.material.icons.automirrored.outlined.ExitToApp
import androidx.compose.material.icons.automirrored.outlined.Send
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.Role.Companion.Button
import androidx.compose.ui.unit.dp
import com.example.di_tema5_hojasinferiores.ui.theme.DI_Tema5_HojasInferioresTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DI_Tema5_HojasInferioresTheme {
                Column {
                    //Ventana()
                    Spacer(modifier = Modifier.padding(30.dp))
                    BasicDropdownMenu()
                    ScrollableDropdownMenu()
                    AdvancedDropdownMenu()
                }
            }
        }
    }
}

// mostrar y ocultar hojas inferiores
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Ventana() {

    var hojaVisible by remember { mutableStateOf(false) }
    val scope = rememberCoroutineScope()
    var estadoVentana = rememberModalBottomSheetState(skipPartiallyExpanded = true)

    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // boton para desplegar una hoja inferior
        Button(onClick = {
            hojaVisible = true
        }
        ) {
            Text("Desplegar Hoja")
        }

        // se lanza la hoja inferior
        if (hojaVisible == true) {
            ModalBottomSheet(
                onDismissRequest = {
                    hojaVisible = false
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                sheetState = estadoVentana
            ) {
                Button(
                    onClick = {
                        scope.launch {
                            hojaVisible = false
                            estadoVentana.hide()
                        }
                    }, modifier = Modifier
                        .align(Alignment.End)
                        .padding(horizontal = 10.dp)
                ) {
                    Text("Ocultar Hoja")
                }
            }
        }
    }
}

@Composable
fun BasicDropdownMenu() {
    var expanded by remember { mutableStateOf(false) }

    Box(modifier = Modifier
        .fillMaxWidth()
        .padding(16.dp)) {
        IconButton(onClick = { expanded = !expanded }) {
            Icon(Icons.Default.MoreVert, contentDescription = "Más opciones")
        }

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            DropdownMenuItem(
                text = { Text("Opción 1") },
                onClick = { /* Acción para la opción 1 */ }
            )
            DropdownMenuItem(
                text = { Text("Opción 2") },
                onClick = { /* Acción para la opción 2 */ }
            )
        }
    }
}

@Composable
fun ScrollableDropdownMenu() {
    var expanded by remember { mutableStateOf(false) }
    val options = List(100) { "Opción ${it + 1}" }

    Box(modifier = Modifier.padding(16.dp)) {
        IconButton(onClick = { expanded = !expanded }) {
            Icon(Icons.Default.MoreVert, contentDescription = "Más opciones")
        }

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            options.forEach { option ->
                DropdownMenuItem(
                    text = { Text(option) },
                    onClick = { /* Acción para $option */ }
                )
            }
        }
    }
}

@Composable
fun AdvancedDropdownMenu() {
    var expanded by remember { mutableStateOf(false) }

    Box(modifier = Modifier.fillMaxWidth().padding(16.dp)) {
        IconButton(onClick = { expanded = !expanded }) {
            Icon(Icons.Default.MoreVert, contentDescription = "Más opciones")
        }

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            // Sección 1
            DropdownMenuItem(
                text = { Text("Perfil") },
                leadingIcon = { Icon(Icons.Outlined.Person, contentDescription = null) },
                onClick = { /* Ir al perfil */ }
            )
            DropdownMenuItem(
                text = { Text("Configuración") },
                leadingIcon = { Icon(Icons.Outlined.Settings, contentDescription = null) },
                onClick = { /* Ir a configuración */ }
            )

            HorizontalDivider()

            // Sección 2
            DropdownMenuItem(
                text = { Text("Enviar feedback") },
                leadingIcon = { Icon(Icons.AutoMirrored.Outlined.ExitToApp, contentDescription = null) },
                trailingIcon = { Icon(Icons.AutoMirrored.Outlined.Send, contentDescription = null) },
                onClick = { /* Enviar comentarios */ }
            )

            HorizontalDivider()

            // Sección 3
            DropdownMenuItem(
                text = { Text("Acerca de") },
                leadingIcon = { Icon(Icons.Outlined.Info, contentDescription = null) },
                onClick = { /* Mostrar información */ }
            )
            DropdownMenuItem(
                text = { Text("Ayuda") },
                leadingIcon = { Icon(Icons.AutoMirrored.Outlined.Send, contentDescription = null) },
                trailingIcon = { Icon(Icons.AutoMirrored.Outlined.ArrowBack, contentDescription = null) },
                onClick = { /* Abrir ayuda */ }
            )
        }
    }
}