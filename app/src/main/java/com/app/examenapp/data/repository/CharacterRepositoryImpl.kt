package com.app.examenapp.data.repository

import com.app.examenapp.data.mapper.toDomain
import com.app.examenapp.data.remote.RickAndMortyApi
import com.app.examenapp.domain.model.Character
import com.app.examenapp.domain.repository.CharacterRepository
import javax.inject.Inject

class CharacterRepositoryImpl
    @Inject
    constructor(
        private val api: RickAndMortyApi,
    ) : CharacterRepository {
        override suspend fun getCharacters(): List<Character> = api.getCharacters().results.map { it.toDomain() }
    }
