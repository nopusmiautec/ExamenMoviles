package com.app.examenapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.app.examenapp.data.local.dao.SudokuDao
import com.app.examenapp.data.local.entity.SudokuEntity

@Database(
    entities = [SudokuEntity::class],
    version = 2,
)
abstract class ExamenDatabase : RoomDatabase() {
    abstract fun sudokuDao(): SudokuDao
}
