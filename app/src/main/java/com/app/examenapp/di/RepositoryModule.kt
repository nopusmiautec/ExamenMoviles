package com.app.examenapp.di

import com.app.examenapp.data.repository.SudokuRepositoryImpl
import com.app.examenapp.domain.repository.SudokuRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Suppress("ktlint:standard:no-empty-first-line-in-class-body")
@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindSudokuRepository(
        sudokuRepositoryImpl: SudokuRepositoryImpl
    ): SudokuRepository
}
