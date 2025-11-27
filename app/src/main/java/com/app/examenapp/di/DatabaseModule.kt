package com.app.examenapp.di

import android.content.Context
import androidx.room.Room
import com.app.examenapp.data.local.ExamenDatabase
import com.app.examenapp.data.local.dao.SudokuDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context,
    ): ExamenDatabase {
        return Room.databaseBuilder(
            context,
            ExamenDatabase::class.java,
            "examen_sudoku_db"
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    fun provideSudokuDao(database: ExamenDatabase): SudokuDao {
        return database.sudokuDao()
    }
}
