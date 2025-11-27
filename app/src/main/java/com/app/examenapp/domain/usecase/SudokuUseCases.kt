package com.app.examenapp.domain.usecase

import com.app.examenapp.domain.model.SudokuModel
import com.app.examenapp.domain.repository.SudokuRepository
import javax.inject.Inject

class SudokuUseCases @Inject constructor(
    private val repository: SudokuRepository
) {

    suspend fun getNewPuzzle(difficulty: String, size: Int): SudokuModel {
        return repository.getNewPuzzle(difficulty, size)
    }

    suspend fun saveGame(game: SudokuModel) {
        repository.saveGame(game)
    }

    suspend fun loadSavedGame(): SudokuModel? {
        return repository.loadSavedGame()
    }
}