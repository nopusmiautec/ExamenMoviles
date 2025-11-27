package com.app.examenapp.data.remote.dto

import com.google.gson.annotations.SerializedName

data class SudokuResponseDto(
    @SerializedName("puzzle")
    val board: List<List<Int?>>?,

    @SerializedName("solution")
    val solution: List<List<Int>>?
)
