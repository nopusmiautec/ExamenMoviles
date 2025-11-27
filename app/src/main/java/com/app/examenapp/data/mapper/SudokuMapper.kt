package com.app.examenapp.data.mapper

import com.app.examenapp.data.local.entity.SudokuEntity
import com.app.examenapp.data.remote.dto.SudokuResponseDto
import com.app.examenapp.domain.model.SudokuModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

val gson = Gson()
val type = object : TypeToken<List<List<Int>>>() {}.type

fun SudokuResponseDto.toDomain(difficulty: String, size: Int): SudokuModel {
    val rawBoard = this.board ?: emptyList()
    val safeSolution = this.solution ?: emptyList()

    val validBoard = if (rawBoard.isNotEmpty()) {
        rawBoard.map { row ->
            row.map { cellValue -> cellValue ?: 0 }
        }
    } else {
        createEmptyBoard(size)
    }

    val current = validBoard.map { it.toMutableList() }.toMutableList()

    return SudokuModel(
        initialBoard = validBoard,
        currentBoard = current,
        solution = safeSolution,
        difficulty = difficulty,
        size = size
    )
}

fun SudokuEntity.toDomain(): SudokuModel {
    val initial: List<List<Int>> = gson.fromJson(initialBoardJson, type)
    val current: MutableList<MutableList<Int>> = gson.fromJson(currentBoardJson, type)
    val solution: List<List<Int>> = gson.fromJson(solutionJson, type)

    return SudokuModel(initial, current, solution, difficulty, size)
}

fun SudokuModel.toEntity(): SudokuEntity {
    return SudokuEntity(
        initialBoardJson = gson.toJson(initialBoard),
        currentBoardJson = gson.toJson(currentBoard),
        solutionJson = gson.toJson(solution),
        difficulty = difficulty,
        size = size
    )
}

fun createEmptyBoard(size: Int): List<List<Int>> {
    return List(size) { List(size) { 0 } }
}