package com.app.examenapp.data.remote

import com.app.examenapp.core.Constants
import com.app.examenapp.data.remote.dto.SudokuResponseDto
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface SudokuApi {
    @GET("sudokugenerate")
    suspend fun getSudoku(
        @Header("X-Api-Key") apiKey: String = Constants.API_KEY,
        @Query("difficulty") difficulty: String,
        @Query("width") width: Int,
        @Query("height") height: Int,
    ): SudokuResponseDto
}
