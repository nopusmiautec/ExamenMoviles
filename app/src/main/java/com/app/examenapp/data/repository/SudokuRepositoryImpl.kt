package com.app.examenapp.data.repository

import com.app.examenapp.data.local.dao.SudokuDao
import com.app.examenapp.data.mapper.toDomain
import com.app.examenapp.data.mapper.toEntity
import com.app.examenapp.data.remote.SudokuApi
import com.app.examenapp.domain.model.SudokuModel
import com.app.examenapp.domain.repository.SudokuRepository
import javax.inject.Inject
import kotlin.math.sqrt

class SudokuRepositoryImpl
    @Inject
    constructor(
        private val api: SudokuApi,
        private val dao: SudokuDao,
    ) : SudokuRepository {
    override suspend fun getNewPuzzle(difficulty: String, size: Int): SudokuModel {
        val boxSize = sqrt(size.toDouble()).toInt()
        val response = api.getSudoku(difficulty = difficulty, width = boxSize, height = boxSize)
        return response.toDomain(difficulty, size)
    }

    override suspend fun saveGame(game: SudokuModel) {
        dao.saveGame(game.toEntity())
    }

    override suspend fun loadSavedGame(): SudokuModel? {
        return dao.getSavedGame()?.toDomain()
    }
}