package com.example.di_tema5_hojasinferiores

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.di_tema5_hojasinferiores.ui.theme.DI_Tema5_HojasInferioresTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DI_Tema5_HojasInferioresTheme {
                Ventana()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Ventana() {

    var hojaVisible by remember { mutableStateOf(false) }
    val scope = rememberCoroutineScope ()
    var estadoVentana = rememberModalBottomSheetState (skipPartiallyExpanded = true)

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
        if(hojaVisible == true) {
            ModalBottomSheet(onDismissRequest = {
                hojaVisible = false
            },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                sheetState = estadoVentana
            ) {
                Button(onClick = {
                    scope.launch{hojaVisible = false
                        estadoVentana.hide()}
                }, modifier = Modifier
                    .align(Alignment.End).
                    padding(horizontal = 10.dp)
                ) {
                    Text("Ocultar Hoja")
                }
            }
        }
    }
}
