package com.app.examenapp.presentation.screens.game.components

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.app.examenapp.domain.model.SudokuModel

@Composable
fun SudokuBoard(
    model: SudokuModel,
    onCellClick: (row: Int, col: Int) -> Unit
) {
    val size = model.size

    Box(
        modifier = Modifier
            .aspectRatio(1f)
            .border(2.dp, Color.Black)
    ) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(size),
            modifier = Modifier.fillMaxSize()
        ) {
            items(size * size) { index ->
                val row = index / size
                val col = index % size

                val value = model.currentBoard[row][col]
                val isInitial = model.initialBoard[row][col] != 0

                val subGridSize = if (size == 4) 2 else 3
                val borderRight = if ((col + 1) % subGridSize == 0 && col != size - 1) 2.dp else 0.5.dp
                val borderBottom = if ((row + 1) % subGridSize == 0 && row != size - 1) 2.dp else 0.5.dp

                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .aspectRatio(1f)
                        .border(
                            width = 0.5.dp,
                            color = Color.Gray
                        )

                        .padding(end = if ((col + 1) % subGridSize == 0) 1.dp else 0.dp,
                            bottom = if ((row + 1) % subGridSize == 0) 1.dp else 0.dp)
                        .clickable { onCellClick(row, col) },
                    contentAlignment = Alignment.Center
                ) {
                    if (value != 0) {
                        Text(
                            text = value.toString(),
                            fontSize = if (size == 9) 18.sp else 24.sp,
                            fontWeight = if (isInitial) FontWeight.Bold else FontWeight.Normal,
                            color = if (isInitial) Color.Black else Color.Blue
                        )
                    }
                }
            }
        }
    }
}