package com.app.examenapp.presentation.screens.game

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.app.examenapp.core.Resource
import com.app.examenapp.presentation.screens.game.components.SudokuBoard

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GameScreen(
    difficulty: String,
    size: Int,
    isNewGame: Boolean,
    viewModel: SudokuViewModel = hiltViewModel(),
    onNavigateBack: () -> Unit
) {
    LaunchedEffect(Unit) {
        viewModel.loadOrNewGame(difficulty, size, isNewGame)
    }

    val state by viewModel.gameState.collectAsState()
    val message by viewModel.verificationMessage.collectAsState()
    val scrollState = rememberScrollState()

    var selectedRow by remember { mutableStateOf(-1) }
    var selectedCol by remember { mutableStateOf(-1) }

    if (message != null) {
        AlertDialog(
            onDismissRequest = { viewModel.clearMessage() },
            confirmButton = { Button(onClick = { viewModel.clearMessage() }) { Text("OK") } },
            title = { Text("Resultado") },
            text = { Text(message!!) }
        )
    }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(title = { Text("Sudoku: $difficulty") },
                navigationIcon = {
                    IconButton(onClick = { onNavigateBack() }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Regresar"
                        )
                    }
                })
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .verticalScroll(scrollState)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            when (val result = state) {
                is Resource.Loading -> CircularProgressIndicator()
                is Resource.Error -> {
                    Text("Error: ${result.message}", color = Color.Red)
                    Button(onClick = { viewModel.loadOrNewGame(difficulty, size, true) }) {
                        Text("Reintentar")
                    }
                }
                is Resource.Success -> {
                    val model = result.data!!

                    SudokuBoard(model = model) { row, col ->
                        selectedRow = row
                        selectedCol = col
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    Text("Celda seleccionada: [${selectedRow + 1}, ${selectedCol + 1}]")

                    NumberPad(size) { number ->
                        if (selectedRow != -1 && selectedCol != -1) {
                            viewModel.onCellInput(selectedRow, selectedCol, number)
                        }
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                        Button(onClick = { viewModel.verifySolution() }) { Text("Verificar") }
                        OutlinedButton(onClick = { viewModel.resetPuzzle() }) { Text("Reiniciar") }
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    Button(
                        onClick = { viewModel.loadOrNewGame(difficulty, size, forceNew = true) },
                        colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.secondary)
                    ) {
                        Text("Nuevo Juego")
                    }
                }
            }
        }
    }
}

@Composable
fun NumberPad(size: Int, onNumberClick: (Int) -> Unit) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(5),
        modifier = Modifier.height(120.dp).fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(4.dp),
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        items(size) { index ->
            val num = index + 1
            Button(onClick = { onNumberClick(num) }) {
                Text(num.toString())
            }
        }
        item {
            Button(onClick = { onNumberClick(0) }, colors = ButtonDefaults.buttonColors(containerColor = Color.Gray)) {
                Text("X")
            }
        }
    }
}