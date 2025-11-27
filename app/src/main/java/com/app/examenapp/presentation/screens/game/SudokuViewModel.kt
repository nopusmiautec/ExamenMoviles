package com.app.examenapp.presentation.screens.game

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.examenapp.core.Resource
import com.app.examenapp.domain.model.SudokuModel
import com.app.examenapp.domain.usecase.SudokuUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SudokuViewModel @Inject constructor(
    private val sudokuUseCases: SudokuUseCases
) : ViewModel() {

    private val _gameState = MutableStateFlow<Resource<SudokuModel>>(Resource.Loading())
    val gameState = _gameState.asStateFlow()

    private val _verificationMessage = MutableStateFlow<String?>(null)
    val verificationMessage = _verificationMessage.asStateFlow()

    fun loadOrNewGame(difficulty: String = "easy", size: Int = 9, forceNew: Boolean = false) {
        viewModelScope.launch {
            _gameState.value = Resource.Loading()
            try {
                if (!forceNew) {
                    val saved = sudokuUseCases.loadSavedGame()
                    if (saved != null) {
                        _gameState.value = Resource.Success(saved)
                        return@launch
                    }
                }
                fetchNewPuzzle(difficulty, size)
            } catch (e: Exception) {
                e.printStackTrace()
                _gameState.value = Resource.Error("Error: ${e.message}")
            }
        }
    }

    private suspend fun fetchNewPuzzle(difficulty: String, size: Int) {
        try {
            val puzzle = sudokuUseCases.getNewPuzzle(difficulty, size)
            _gameState.value = Resource.Success(puzzle)
            saveGame()
        } catch (e: Exception) {
            e.printStackTrace()
            _gameState.value = Resource.Error("Error de conexión.")
        }
    }

    fun onCellInput(row: Int, col: Int, number: Int) {
        val currentResource = _gameState.value
        if (currentResource is Resource.Success) {
            val model = currentResource.data!!
            if (model.initialBoard[row][col] == 0) {
                model.currentBoard[row][col] = number
                _gameState.value = Resource.Success(model.copy(currentBoard = model.currentBoard))
                saveGame()
            }
        }
    }

    fun verifySolution() {
        val currentResource = _gameState.value
        if (currentResource is Resource.Success) {
            val model = currentResource.data!!
            val isCorrect = model.currentBoard == model.solution

            if (isCorrect) {
                _verificationMessage.value = "¡Felicidades! Solución Correcta!"
            } else {
                _verificationMessage.value = "Hay errores, sigue intentando."
            }
        }
    }

    fun clearMessage() { _verificationMessage.value = null }

    fun resetPuzzle() {
        val currentResource = _gameState.value
        if (currentResource is Resource.Success) {
            val model = currentResource.data!!

            val resetBoard = model.initialBoard.map { it.toMutableList() }.toMutableList()
            _gameState.value = Resource.Success(model.copy(currentBoard = resetBoard))
            saveGame()
            _verificationMessage.value = "Tablero reiniciado"
        }
    }

    private fun saveGame() {
        val currentResource = _gameState.value
        if (currentResource is Resource.Success) {
            viewModelScope.launch {
                sudokuUseCases.saveGame(currentResource.data!!)
            }
        }
    }
}