package com.app.examenapp.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "sudoku_table")
data class SudokuEntity(
    @PrimaryKey val id: Int = 1,
    val initialBoardJson: String,
    val currentBoardJson: String,
    val solutionJson: String,
    val difficulty: String,
    val size: Int,
)
