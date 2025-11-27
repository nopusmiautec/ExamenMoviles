package com.app.examenapp.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.app.examenapp.data.local.entity.SudokuEntity

@Dao
interface SudokuDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveGame(game: SudokuEntity)

    @Query("SELECT * FROM sudoku_table WHERE id = 1")
    suspend fun getSavedGame(): SudokuEntity?

    @Query("SELECT COUNT(*) FROM sudoku_table")
    suspend fun hasSavedGame(): Int
}
