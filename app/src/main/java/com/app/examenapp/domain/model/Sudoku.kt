package com.app.examenapp.domain.model

data class SudokuModel(
    val initialBoard: List<List<Int>>,
    val currentBoard: MutableList<MutableList<Int>>,
    val solution: List<List<Int>>,
    val difficulty: String,
    val size: Int,
)

data class SudokuCell(
    val value: Int,
    val isInitial: Boolean,
    val row: Int,
    val col: Int,
)
