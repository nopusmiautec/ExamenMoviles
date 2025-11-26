package com.app.examenapp.domain.repository

import com.app.examenapp.domain.model.Character

interface CharacterRepository {
    suspend fun getCharacters(): List<Character>
}
