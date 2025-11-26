package com.app.examenapp.di

import android.content.Context
import androidx.room.Room
import com.app.examenapp.data.local.ExamenDatabase
import com.app.examenapp.data.local.dao.CharacterDao
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
    ): ExamenDatabase =
        Room
            .databaseBuilder(
                context,
                ExamenDatabase::class.java,
                "examen_db",
            ).build()

    @Provides
    fun provideCharacterDao(database: ExamenDatabase): CharacterDao = database.characterDao()
}
