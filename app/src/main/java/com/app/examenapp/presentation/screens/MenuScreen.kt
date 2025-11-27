package com.app.examenapp.presentation.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MenuScreen(
    onNavigateToGame: (difficulty: String, size: Int, isNew: Boolean) -> Unit,
    onNavigateBack: () -> Unit
) {
    val scrollState = rememberScrollState()
    var selectedSize by remember { mutableIntStateOf(9) }
    var selectedDiff by remember { mutableStateOf("easy") }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Configurar Sudoku") },
                navigationIcon = {
                    IconButton(onClick = { onNavigateBack() }) {
                        Icon(imageVector = Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Regresar")
                    }
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier.fillMaxSize().padding(padding).padding(16.dp).verticalScroll(scrollState),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Tamaño")
            Row {
                FilterChip(selected = selectedSize == 4, onClick = { selectedSize = 4 }, label = { Text("4x4") })
                Spacer(modifier = Modifier.width(8.dp))
                FilterChip(selected = selectedSize == 9, onClick = { selectedSize = 9 }, label = { Text("9x9") })
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text("Dificultad")
            Row {
                FilterChip(selected = selectedDiff == "easy", onClick = { selectedDiff = "easy" }, label = { Text("Fácil") })
                Spacer(modifier = Modifier.width(8.dp))
                FilterChip(selected = selectedDiff == "medium", onClick = { selectedDiff = "medium" }, label = { Text("Medio") })
                Spacer(modifier = Modifier.width(8.dp))
                FilterChip(selected = selectedDiff == "hard", onClick = { selectedDiff = "hard" }, label = { Text("Dificil") })
            }

            Spacer(modifier = Modifier.height(32.dp))

            Button(
                onClick = { onNavigateToGame(selectedDiff, selectedSize, true) },
                modifier = Modifier.fillMaxWidth()
            ) { Text("Juego Nuevo") }

            Spacer(modifier = Modifier.height(8.dp))

            OutlinedButton(
                onClick = { onNavigateToGame("easy", 9, false) },
                modifier = Modifier.fillMaxWidth()
            ) { Text("Continuar Guardada") }
        }
    }
}