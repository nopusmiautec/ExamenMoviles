package com.app.examenapp.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.app.examenapp.data.local.entity.CharacterEntity

@Dao
interface CharacterDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCharacters(characters: List<CharacterEntity>)

    @Query("DELETE FROM character_table")
    suspend fun clearCharacters()

    @Query("SELECT * FROM character_table")
    suspend fun getCharacters(): List<CharacterEntity>
}
