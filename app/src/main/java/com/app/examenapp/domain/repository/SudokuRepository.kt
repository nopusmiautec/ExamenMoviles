package com.app.examenapp.domain.repository

import com.app.examenapp.domain.model.SudokuModel

interface SudokuRepository {
    suspend fun getNewPuzzle(difficulty: String, size: Int): SudokuModel
    suspend fun saveGame(game: SudokuModel)
    suspend fun loadSavedGame(): SudokuModel?
}
